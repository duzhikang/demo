package com.rudy.elasticsearch.javaapi;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>ClassName: TestService</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2020/4/22
 * @since JDK 1.8
 */
@Slf4j
@Service
public class IndexApiService {

    @Resource
    private TransportClient client;
    
    /**
     * @Param 
     * @Description 
     * @Author zhikang.du
     * @Date 2020/4/22
     * @return 
     **/
    public void indexDocument() {
        try {
            IndexResponse response = client.prepareIndex("twitter", "_doc", "1")
                    .setSource(XContentFactory.jsonBuilder()
                            .startObject()
                            .field("user", "kimchy")
                            .field("postDate", new Date())
                            .field("message", "trying out Elasticsearch")
                            .endObject()
                    )
                    .get();
            log.info("{}, {}, {}, {}, {}", response.getIndex(), response.getType(),
                    response.getId(), response.getVersion(), response.status());
        }catch (Exception e) {

        }
    }
}
