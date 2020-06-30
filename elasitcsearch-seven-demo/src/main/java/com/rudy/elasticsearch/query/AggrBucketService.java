package com.rudy.elasticsearch.query;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.rest.RestStatus;

import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.histogram.HistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.range.DateRangeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ParsedTopHits;
import org.elasticsearch.search.aggregations.metrics.TopHitsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * <p>ClassName: AggrBucketService</p>
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
public class AggrBucketService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /*GET user/_search
    {
        "size": 0,
            "aggs": {
        "age_bucket": {
            "terms": {
                "field": "age",
                        "size": "10"
            }
        }
    }
    }*/

    /**
     * 按岁数进行聚合分桶
     */
    public void aggrBucketTerms() {
        try {
            AggregationBuilder aggr = AggregationBuilders.terms("age_bucket").field("age");
            //
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.size(10);
            searchSourceBuilder.aggregation(aggr);
            // 创建查询请求对象，讲查询条件配置到其中
            SearchRequest searchRequest = new SearchRequest();
            searchRequest.source(searchSourceBuilder);

            SearchResponse response  = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            Aggregations aggregations = response.getAggregations();
            if (RestStatus.OK.equals(response.status())) {
                // 分桶
                Terms byCompanyAggregation  = aggregations.get("age_bucket");
                List<? extends Terms.Bucket> buckets = byCompanyAggregation.getBuckets();
                // 输出各个桶的内容
                log.info("-------------------------------------------");
                log.info("聚合信息:");
                for (Terms.Bucket bucket : buckets) {
                    log.info("桶名：{} | 总数：{}", bucket.getKeyAsString(), bucket.getDocCount());
                }
                log.info("-------------------------------------------");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*GET user/_search
    {
        "aggs": {
        "salary_range_bucket": {
            "range": {
                "field": "salary",
                        "ranges": [
                {
                    "key": "低级员工",
                        "to": 3000
                },{
                    "key": "中级员工",
                            "from": 5000,
                            "to": 9000
                },{
                    "key": "高级员工",
                            "from": 9000
                }
        ]
            }
        }
    }
    }*/
    /**
     * 按工资范围进行聚合分桶
     */
    public void aggrBucketRange() {
        try {
            AggregationBuilder aggr = AggregationBuilders.range("salary_range_bucket")
                    .field("salary")
                    .addUnboundedTo("低级员工", 3000)
                    .addRange("中级员工", 5000, 9000)
                    .addUnboundedFrom("高级员工", 9000);
            // 查询源构造器
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.size(0);
            searchSourceBuilder.aggregation(aggr);

            SearchRequest searchRequest = new SearchRequest("user");
            searchRequest.source(searchSourceBuilder);

            SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            Aggregations aggregations = response.getAggregations();
            // 输出内容
            if (RestStatus.OK.equals(response.status())) {
                Range range = aggregations.get("salary_range_bucket");
                List<? extends Range.Bucket> buckets = range.getBuckets();
                for (Range.Bucket bucket : buckets) {
                    log.info("桶名：{} | 总数：{}", bucket.getKeyAsString(), bucket.getDocCount());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*GET user/_search
    {
        "size": 10,
            "aggs": {
        "date_range_bucket": {
            "date_range": {
                "field": "birthDate",
                        "format": "yyyy",
                        "ranges": [
                {
                    "key": "出生日期1985-1990的员工",
                        "from": "1985",
                        "to": "1990"
                },{
                    "key": "出生日期1990-1995的员工",
                            "from": "1990",
                            "to": "1995"
                }
        ]
            }
        }
    }
    }*/
    /**
     * 按照时间范围进行分桶
     */
    public void aggrBucketDateRange() {
        try {
            DateRangeAggregationBuilder aggr = AggregationBuilders.dateRange("date_range_bucket").field("birthDate")
                    .format("yyyy")
                    .addRange("1985-1990", "1985", "1900")
                    .addRange("1990-1995", "1990", "1995");
            // 查询源构造器
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.size(0);
            searchSourceBuilder.aggregation(aggr);
            //
            SearchRequest searchRequest = new SearchRequest();
            searchRequest.source(searchSourceBuilder);

            SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            if (RestStatus.OK.equals(response.status())) {
                Aggregations aggregations = response.getAggregations();
                Range byCompanyAggregation = aggregations.get("date_range_bucket");
                List<? extends Range.Bucket> buckets = byCompanyAggregation.getBuckets();
                for (Range.Bucket bucket : buckets) {
                    log.info("桶名：{} | 总数：{}", bucket.getKeyAsString(), bucket.getDocCount());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*GET user/_search
    {
        "size": 0,
            "aggs": {
        "salary_histogram": {
            "histogram": {
                "field": "salary",
                        "extended_bounds": {
                    "min": 0,
                            "max": 12000
                },
                "interval": 3000
            }
        }
    }
    }*/
    /**
     * 按工资多少进行聚合分桶
     */
    public void aggrBucketHistogram() {
        try {
            HistogramAggregationBuilder aggr = AggregationBuilders.histogram("salary_histogram")
                    .field("salary")
                    .extendedBounds(0, 12000)
                    .interval(3000);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.size(0);
            searchSourceBuilder.aggregation(aggr);

            //
            SearchRequest searchRequest = new SearchRequest("user");
            searchRequest.source(searchSourceBuilder);

            SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            Aggregations aggregations = response.getAggregations();
            if (RestStatus.OK.equals(response.status())) {
                Histogram byCompanyAggregation = aggregations.get("salary_histogram");
                List<? extends Histogram.Bucket> buckets = byCompanyAggregation.getBuckets();
                for (Histogram.Bucket bucket : buckets) {
                    log.info("桶名：{} | 总数：{}", bucket.getKeyAsString(), bucket.getDocCount());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*GET user/_search
    {
        "size": 0,
            "aggs": {
        "birthday_histogram": {
            "date_histogram": {
                "format": "yyyy",
                        "field": "birthDate",
                        "interval": "year"
            }
        }
    }
    }*/
    /**
     * 按出生日期进行分桶
     */
    public void aggrBucketDateHistogram() {
        try {
            AggregationBuilder aggr = AggregationBuilders.dateHistogram("birthday_histogram")
                    .field("birthDate")
                    .interval(1)
                    .dateHistogramInterval(DateHistogramInterval.YEAR)
                    .format("yyyy");
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.size(0);
            searchSourceBuilder.aggregation(aggr);

            //
            SearchRequest searchRequest = new SearchRequest("user");
            searchRequest.source(searchSourceBuilder);

            SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            Aggregations aggregations = response.getAggregations();
            if (RestStatus.OK.equals(response.status())) {
                Histogram byCompanyAggregation = aggregations.get("salary_histogram");
                List<? extends Histogram.Bucket> buckets = byCompanyAggregation.getBuckets();
                for (Histogram.Bucket bucket : buckets) {
                    log.info("桶名：{} | 总数：{}", bucket.getKeyAsString(), bucket.getDocCount());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*GET user/_search
    {
        "size": 0,
            "aggs": {
        "salary_bucket": {
            "terms": {
                "field": "age",
                        "size": "10"
            },
            "aggs": {
                "salary_max_user": {
                    "top_hits": {
                        "size": 1,
                                "sort": [
                        {
                            "salary": {
                            "order": "desc"
                        }
                        }
            ]
                    }
                }
            }
        }
    }
    }*/
    /**
     * topHits 按岁数分桶、然后统计每个员工工资最高值
     */
    public void aggregationTopHits() {
        try {
            TopHitsAggregationBuilder testTop  = AggregationBuilders.topHits("salary_max_user")
                    .size(1)
                    .sort("salary", SortOrder.DESC);
            TermsAggregationBuilder salaryBucket = AggregationBuilders.terms("salary_bucket")
                    .field("age")
                    .size(10);
            salaryBucket.subAggregation(testTop);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.size(0);
            searchSourceBuilder.aggregation(salaryBucket);

            //
            SearchRequest searchRequest = new SearchRequest("user");
            searchRequest.source(searchSourceBuilder);

            SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            if (RestStatus.OK.equals(response.status())) {
                Aggregations aggregations = response.getAggregations();
                Terms byCompanyAggregation = aggregations.get("salary_bucket");
                List<? extends Terms.Bucket> buckets = byCompanyAggregation.getBuckets();
                for (Terms.Bucket bucket : buckets) {
                    log.info("桶名：{}", bucket.getKeyAsString());
                    ParsedTopHits topHits = bucket.getAggregations().get("salary_max_user");
                    for (SearchHit hit : topHits.getHits()) {
                        log.info(hit.getSourceAsString());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
