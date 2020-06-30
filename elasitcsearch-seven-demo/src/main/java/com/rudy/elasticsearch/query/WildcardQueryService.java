package com.rudy.elasticsearch.query;

import com.alibaba.fastjson.JSON;
import com.rudy.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * <p>ClassName: WildcardQueryService</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2020/6/30
 * @since JDK 1.8
 */
@Slf4j
@Service
public class WildcardQueryService {

    @Resource
    private RestHighLevelClient restHighLevelClient;

/*    GET user/_search
    {
        "query": {
        "wildcard": {
            "name.keyword": {
                "value": "*三"
            }
        }
    }
    }*/
    /**
     * 查询所有以 “三” 结尾的姓名
     *
     * *：表示多个字符（0个或多个字符）
     * ?：表示单个字符
     */
    public void wildcardQuery() {
        try {
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.wildcardQuery("name.keyword", "*三"));

            SearchRequest searchRequest = new SearchRequest("user");
            searchRequest.source(searchSourceBuilder);

            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            if (RestStatus.OK.equals(searchResponse.status()) && searchResponse.getHits().getTotalHits().value > 0) {
                SearchHits hits = searchResponse.getHits();
                for (SearchHit hit : hits) {
                    // 将 JSON 转换成对象
                    UserInfo userInfo = JSON.parseObject(hit.getSourceAsString(), UserInfo.class);
                    // 输出查询信息
                    log.info("user==={}", userInfo.toString());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
