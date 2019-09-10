package com.zk.es.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zk.basic.DocOperatorEnum;
import com.zk.basic.DomainManageTerm;
import com.zk.basic.IndexConstant;
import com.zk.bo.DocDomainManage;
import com.zk.bo.DomainIndexMessage;
import com.zk.es.DomainManageIndexService;
import com.zk.gson.GsonCustomerDateJsonSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.IndexComponent;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.rest.RestStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

/**
 * <p>ClassName: MqDomainManageIndexServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/9/9
 * @since JDK 1.8
 */
@Service("kafkaService")
@Slf4j
public class MqDomainManageIndexServiceImpl implements DomainManageIndexService {

    @Autowired
    private TransportClient client;

    /**
     * 对象映射
     */
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    private static Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonCustomerDateJsonSerializer()).create();

    private static final Integer RETRY_MAX = 3;

    /**
     * kafka topic
     */
    private static final String INDEX_TOPIC = "topic_domain";

    @KafkaListener(topics = INDEX_TOPIC)
    private void handleMessage(ConsumerRecord record) {
        String content = (String) record.value();
        DomainIndexMessage message = gson.fromJson(content, DomainIndexMessage.class);

        switch (message.getOperatorEnum().getStr()) {
            case "index":
                this.index(message);
                break;
            case "remove":
                this.remove(message);
                break;
            default:
                log.warn("handleMessage Not support message content {}", content);
                break;
        }
    }


    @Override
    public void index(DocDomainManage doc) {
        this.index(doc, 0);
    }

    private void index(DocDomainManage doc, int retry) {
        if (retry > RETRY_MAX) {
            log.error("构建索引重试次数超过三次");
            return;
        }
        DomainIndexMessage message = new DomainIndexMessage(retry, DocOperatorEnum.INDEX, doc);
        String json = gson.toJson(message);
        kafkaTemplate.send(INDEX_TOPIC, json);
    }

    private void index(DomainIndexMessage<?> domainIndexMessage) {
        DocDomainManage date =  gson.fromJson(gson.toJson(domainIndexMessage.getDate()), DocDomainManage.class);
        String domain = date.getDomain();
        // 查询es，检查数据是否存在
        SearchResponse searchResponse = this.client.prepareSearch(IndexConstant.INDEX_DOMAIN_MANAGE)
                .setTypes(IndexConstant.INDEX_TYPE)
                .setQuery(QueryBuilders.termQuery(DomainManageTerm.MANAGE_ID, date.getManageId()))
                .get();
        long totalHits = searchResponse.getHits().getTotalHits();
        boolean result = false;
        if (totalHits == 0) {
            result = create(date);
        }
        if (totalHits == 1) {
            String esId = searchResponse.getHits().getAt(0).getId();
            result = update(esId, date);
        }
        if (totalHits > 1) {
            result = deleteAndCreate(totalHits, date);
        }
        if (!result) {
            this.index(date, domainIndexMessage.getRetry() + 1);
        }
    }

    public boolean create(DocDomainManage doc) {
        boolean flag = false;
        try {
            IndexResponse indexResponse = this.client.prepareIndex(IndexConstant.INDEX_DOMAIN_MANAGE, IndexConstant.INDEX_TYPE)
                    .setSource(gson.toJson(doc), XContentType.JSON)
                    .get();
            if (indexResponse.status() == RestStatus.CREATED) {
                flag = true;
            }
        }catch (Exception e) {
            log.error("Error to create index domain {}, [exception]----{}" , doc.toString(), e.getMessage());
        }
        return flag;
    }

    public boolean update(String esId, DocDomainManage doc) {
        boolean flag = false;
        try {
            UpdateResponse updateResponse = this.client.prepareUpdate(IndexConstant.INDEX_DOMAIN_MANAGE,
                    IndexConstant.INDEX_TYPE, esId)
                    .setDoc(gson.toJson(doc), XContentType.JSON)
                    .get();
            log.info("update start ...., domain==={}", doc.toString());
            if (updateResponse.status() == RestStatus.OK) {
                flag = true;
            }
        }catch (Exception e) {
            log.error("update index is error, domain==={}", doc.getDomain());
        }
        return flag;
    }

    private boolean deleteAndCreate(long totalHits, DocDomainManage doc) {
        boolean flag = false;
        try {
            BulkByScrollResponse response = DeleteByQueryAction.INSTANCE
                    .newRequestBuilder(this.client)
                    .filter(QueryBuilders.termQuery(DomainManageTerm.MANAGE_ID, doc.getManageId()))
                    .source(IndexConstant.INDEX_DOMAIN_MANAGE)
                    .get();
            long deleteCount = response.getDeleted();
            if (deleteCount != totalHits) {
                log.error("need delete {}, but {} was deleted", totalHits, deleteCount);
                boolean result = create(doc);
                if (!result) {
                    log.error("detele date is inconsistent, create index error === {}", doc.getDomain());
                }
            }else {
                flag = true;
            }
        }catch (Exception e) {
            log.error("deleteAndCreate error, exception === {}", e.getMessage());
        }
        return flag;
    }

    @Override
    public void deleteIndex(Long id) {
        this.remove(id, 0);
    }

    /**
     * 删除doc
     * @param id
     * @param retry 重试次数
     */
    private void remove(Long id, int retry) {
        if (retry > RETRY_MAX) {
            log.error("[remove]retry time over 3, domain_id==={}", id);
        }
        DomainIndexMessage message = new DomainIndexMessage(retry, DocOperatorEnum.DELETE, id);
        String json = gson.toJson(message);
        kafkaTemplate.send(INDEX_TOPIC, json);
    }

    private boolean remove(DomainIndexMessage<?> message) {
        boolean flag = false;
        Long id = (Long) message.getDate();
        SearchResponse searchResponse = this.client.prepareSearch(IndexConstant.INDEX_DOMAIN_MANAGE)
                .setTypes(IndexConstant.INDEX_TYPE)
                .setQuery(QueryBuilders.termQuery(DomainManageTerm.MANAGE_ID, id))
                .get();
        long totalHits = searchResponse.getHits().getTotalHits();
        BulkByScrollResponse response = DeleteByQueryAction.INSTANCE
                .newRequestBuilder(this.client)
                .filter(QueryBuilders.termQuery(DomainManageTerm.MANAGE_ID, id))
                .source(IndexConstant.INDEX_DOMAIN_MANAGE)
                .get();
        long deleted = response.getDeleted();
        if (deleted != totalHits) {
            log.error("delete error..");
            this.remove(id, message.getRetry() + 1);
        }else {
            flag = true;
        }
        return flag;
     }

    @Override
    public void updateIndex(DocDomainManage doc) {

    }
}
