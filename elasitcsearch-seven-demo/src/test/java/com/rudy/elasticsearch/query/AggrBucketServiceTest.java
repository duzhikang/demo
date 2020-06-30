package com.rudy.elasticsearch.query;

import com.rudy.ElasitcsearchSevenDemoApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class AggrBucketServiceTest extends ElasitcsearchSevenDemoApplicationTests {

    @Autowired
    private AggrBucketService aggrBucketService;

    @Test
    void aggrBucketTerms() {
        aggrBucketService.aggrBucketTerms();
    }

    @Test
    void aggrBucketRange() {
        aggrBucketService.aggrBucketRange();
    }

    @Test
    void aggrBucketDateRange() {
        aggrBucketService.aggrBucketDateRange();
    }

    @Test
    void aggrBucketDateHistogram() {
        aggrBucketService.aggrBucketDateRange();
    }

    @Test
    void aggrBucketHistogram() {
        aggrBucketService.aggrBucketHistogram();
    }

    @Test
    void aggregationTopHits() {
        aggrBucketService.aggregationTopHits();
    }
}