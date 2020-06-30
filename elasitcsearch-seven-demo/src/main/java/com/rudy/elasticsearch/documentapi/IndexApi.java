package com.rudy.elasticsearch.documentapi;

import com.alibaba.fastjson.JSON;
import com.rudy.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

/**
 * <p>ClassName: IndexApi</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2020/6/29
 * @since JDK 1.8
 */
@Slf4j
@Service
public class IndexApi {

    @Autowired
    private RestHighLevelClient restHighLevelClient;


    /**
     * 创建索引
     */
    public void createIndex() {
        try {
            // 创建 Mapping
            XContentBuilder mapping = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("dynamic", true)
                    .startObject("properties")
                    .startObject("name")
                    .field("type","text")
                    .startObject("fields")
                    .startObject("keyword")
                    .field("type","keyword")
                    .endObject()
                    .endObject()
                    .endObject()
                    .startObject("address")
                    .field("type","text")
                    .startObject("fields")
                    .startObject("keyword")
                    .field("type","keyword")
                    .endObject()
                    .endObject()
                    .endObject()
                    .startObject("remark")
                    .field("type","text")
                    .startObject("fields")
                    .startObject("keyword")
                    .field("type","keyword")
                    .endObject()
                    .endObject()
                    .endObject()
                    .startObject("age")
                    .field("type","integer")
                    .endObject()
                    .startObject("salary")
                    .field("type","float")
                    .endObject()
                    .startObject("birthDate")
                    .field("type","date")
                    .field("format", "yyyy-MM-dd")
                    .endObject()
                    .startObject("createTime")
                    .field("type","date")
                    .endObject()
                    .endObject()
                    .endObject();
            // 创建索引配置信息，配置
            Settings settings = Settings.builder()
                    .put("index.number_of_shards", 1)
                    .put("index.number_of_replicas", 0)
                    .build();
            // 新建创建索引请求对象，然后设置索引类型（ES 7.0 将不存在索引类型）和 mapping 与 index 配置
            CreateIndexRequest request = new CreateIndexRequest("user");
            request.settings(settings);
            request.mapping(mapping);
            // request.mapping("doc", mapping);
            // RestHighLevelClient 执行创建索引
            CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
            // 判断是否创建成功
            boolean isCreated = createIndexResponse.isAcknowledged();
            log.info("是否创建成功：{}", isCreated);
        } catch (IOException e) {
            log.error("", e);
        }
    }

    /**
     * 删除索引
     */
    public void deleteIndex() {
        try {
            // 新建删除索引请求对象
            DeleteIndexRequest request = new DeleteIndexRequest("user");
            // 执行删除索引
            AcknowledgedResponse acknowledgedResponse = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
            // 判断是否删除成功
            boolean siDeleted = acknowledgedResponse.isAcknowledged();
            log.info("是否删除成功：{}", siDeleted);
        } catch (IOException e) {
            log.error("", e);
        }
    }

    /**
     * 增加文档信息
     */
    public void addDocument() {
        try {
            // 创建索引请求对象
            IndexRequest indexRequest = new IndexRequest("user");
            // 创建员工信息
            UserInfo userInfo = new UserInfo();
            userInfo.setName("张三");
            userInfo.setAge(29);
            userInfo.setSalary(100.00f);
            userInfo.setAddress("北京市");
            userInfo.setRemark("来自北京市的张先生");
            userInfo.setCreateTime(new Date());
            userInfo.setBirthDate("1990-01-10");
            // 将对象转换为 byte 数组 fastjson
            byte[] json = JSON.toJSONBytes(userInfo);
            // 设置文档内容
            indexRequest.source(json, XContentType.JSON);
            // 执行增加文档
            IndexResponse response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            log.info("创建状态：{}", response.status());
        } catch (Exception e) {
            log.error("", e);
        }
    }

    /**
     * 添加文档
     */
    public void addDocument2() throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.field("name", "kimchy");
            builder.field("address", "上海");
            builder.field("remark", "test");
            builder.field("age", 19);
            builder.field("salary", 550.55f);
            builder.timeField("createTime", System.currentTimeMillis());
            builder.field("birthDate", "1990-11-10");
        }
        builder.endObject();
        IndexRequest request = new IndexRequest("user").source(builder);
        IndexResponse index = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        log.info("创建状态：{}", index.status());
    }

    /**
     * @Param
     * @Description 查询文档
     * @Author zhikang.du
     * @Date 2020/6/29
     * @return
     **/
    public String getDocument() {
        GetRequest getRequest = new GetRequest("user", "4Omx_nIBOX-uipdzuScG");
        try {
            GetResponse response = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
            // 将 JSON 转换成对象
            if (response.isExists()) {
                UserInfo userInfo = JSON.parseObject(response.getSourceAsBytes(), UserInfo.class);
                log.info("员工信息：{}", userInfo);
                return response.getId();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Param
     * @Description 更新文档
     * @Author zhikang.du
     * @Date 2020/6/29
     * @return
     **/
    public void updateDocument() {
        String id = getDocument();
        UpdateRequest updateRequest = new UpdateRequest("user", id);
        // 设置员工更新信息
        UserInfo userInfo = new UserInfo();
        userInfo.setSalary(200.00f);
        userInfo.setAddress("北京市海淀区");
        // 将对象转换为 byte 数组
        byte[] json = JSON.toJSONBytes(userInfo);
        // 设置更新文档内容
        updateRequest.doc(json, XContentType.JSON);
        try {
            UpdateResponse response = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
            log.info("创建状态：{}", response.status());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除文档信息
     */
    public void deleteDocument() {
        String id = getDocument();
        DeleteRequest deleteRequest = new DeleteRequest("user", id);
        DeleteResponse response = null;
        try {
            response = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
            log.info("删除状态：{}", response.status());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
