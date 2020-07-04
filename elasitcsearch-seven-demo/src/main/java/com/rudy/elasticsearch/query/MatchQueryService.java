package com.rudy.elasticsearch.query;

import com.alibaba.fastjson.JSON;
import com.rudy.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * <p>ClassName: MatchQueryService</p>
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
public class MatchQueryService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * GET user/_search
     * {
     *   "query": {
     *     "match_all": {}
     *   },
     *   "from": 0,
     *   "size": 10,
     *   "sort": [
     *     {
     *       "salary": {
     *         "order": "asc"
     *       }
     *     }
     *   ]
     * }
     * 匹配查询符合条件的所有数据，并设置分页
     */
    public void matchAllQuery() {
        try {
            MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
            //
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(matchAllQueryBuilder);
            // 分页
            searchSourceBuilder.from(0);
            searchSourceBuilder.size(10);
            // 排序
            searchSourceBuilder.sort("salary", SortOrder.DESC);
            // 创建查询对象
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * GET user/_search
     * {
     *   "query": {
     *     "match": {
     *       "address": "通州区"
     *     }
     *   }
     * }
     */
    public void matchQuery() {
        try {
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.matchQuery("address", "通州区"));

            SearchRequest searchRequest = new SearchRequest("user");
            searchRequest.source(searchSourceBuilder);
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

            if (RestStatus.OK.equals(searchResponse.status()
            ) && searchResponse.getHits().getTotalHits().value > 0) {
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

    /**
     * GET user/_search
     * {
     *   "query": {
     *     "multi_match": {
     *       "query": "北京",
     *       "fields": ["address","remark"]
     *     }
     *   }
     * }
     * 内容在多字段中进行查询
     */
    public void matchMultiQuery() {
        try {
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.multiMatchQuery("北京市", "address", "remark"));

            SearchRequest searchRequest = new SearchRequest("user");
            searchRequest.source(searchSourceBuilder);

            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            if (RestStatus.OK.equals(searchResponse.status()) && searchResponse.getHits().getTotalHits().value > 0) {
                SearchHits hits = searchResponse.getHits();
                for (SearchHit hit : hits) {
                    // 将 JSON 转换成对象
                    UserInfo userInfo = JSON.parseObject(hit.getSourceAsString(), UserInfo.class);
                    // 输出查询信息
                    log.info(userInfo.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
