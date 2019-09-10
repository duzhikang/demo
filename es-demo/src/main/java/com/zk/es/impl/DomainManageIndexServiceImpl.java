package com.zk.es.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zk.bo.DocDomainManage;
import com.zk.basic.DomainManageTerm;
import com.zk.basic.IndexConstant;
import com.zk.es.DomainManageIndexService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.rest.RestStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>ClassName: DomainManageIndexServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/9/4
 * @since JDK 1.8
 */
@Service("domainService")
@Slf4j
public class DomainManageIndexServiceImpl implements DomainManageIndexService {

    @Autowired
    private TransportClient client;

    /**
     * jackson
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 对象映射
     */
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void index(DocDomainManage doc) {
        String domain = doc.getDomain();
        try {
            IndexResponse indexResponse = this.client.prepareIndex(IndexConstant.INDEX_DOMAIN_MANAGE, IndexConstant.INDEX_TYPE)
                    .setSource(objectMapper.writeValueAsBytes(doc), XContentType.JSON)
                    .get();
            log.info("Create index with domain by --{}", domain);
            if (indexResponse.status() != RestStatus.CREATED) {
                throw new Exception("create index with domain failure" + indexResponse.status());
            }
        }catch (Exception e) {
            log.error("Error to index domain {}, [exception]----{}" , doc.toString(), e.getMessage());
        }
    }

    @Override
    public void deleteIndex(Long id) {
        try {
            BulkByScrollResponse response = DeleteByQueryAction.INSTANCE.newRequestBuilder(this.client)
                    .filter(QueryBuilders.termQuery(DomainManageTerm.MANAGE_ID, id))
                    .source(IndexConstant.INDEX_DOMAIN_MANAGE)
                    .get();
            log.info("delete index with domain by id --{}", id);
            long deleted = response.getDeleted();
            if (deleted <= 0) {
                throw new Exception("delete index with domain failure");
            }
        }catch (Exception e) {
            log.error("Error to delete index with domain {}, [exception]----{}" , id, e.getMessage());
        }
    }

    @Override
    public void updateIndex(DocDomainManage doc) {
        try {

            // es查询
            SearchResponse searchResponse = this.client.prepareSearch(IndexConstant.INDEX_DOMAIN_MANAGE).setTypes(IndexConstant.INDEX_TYPE)
                    .setQuery(QueryBuilders.termQuery(DomainManageTerm.MANAGE_ID, doc.getManageId())).get();
            long hits = searchResponse.getHits().getTotalHits();
            if (hits ==0) {
                index(doc);
            }
            if (hits == 1) {
                String id = searchResponse.getHits().getAt(0).getId();
                UpdateResponse updateResponse = this.client.prepareUpdate(IndexConstant.INDEX_DOMAIN_MANAGE, IndexConstant.INDEX_TYPE, id)
                        .setDoc(objectMapper.writeValueAsBytes(doc), XContentType.JSON)
                        .get();
                log.info("update domain by --{}", doc.getDomain());
                if (updateResponse.status() == RestStatus.OK) {
                    throw new Exception("update domain failure" + updateResponse.status());
                }
            }
            if (hits !=0 && hits !=1) {
                deleteIndex(doc.getManageId());
                index(doc);
            }
        }catch (Exception e) {
            log.error("update domain error {}, [exception]----{}" , doc.toString(), e.getMessage());
        }
    }
}
