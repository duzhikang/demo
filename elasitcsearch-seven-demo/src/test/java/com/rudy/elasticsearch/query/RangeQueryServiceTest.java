package com.rudy.elasticsearch.query;

import com.rudy.ElasitcsearchSevenDemoApplicationTests;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class RangeQueryServiceTest extends ElasitcsearchSevenDemoApplicationTests {

    @Autowired
    private RangeQueryService rangeQueryService;

    @Test
    void rangeQuery() {
        rangeQueryService.rangeQuery();
    }

    @Test
    void dateRangeQuery() {
        rangeQueryService.dateRangeQuery();
    }
}