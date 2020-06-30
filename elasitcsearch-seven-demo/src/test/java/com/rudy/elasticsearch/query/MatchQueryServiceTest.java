package com.rudy.elasticsearch.query;

import com.rudy.ElasitcsearchSevenDemoApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class MatchQueryServiceTest extends ElasitcsearchSevenDemoApplicationTests {

    @Autowired
    private MatchQueryService matchQueryService;

    @Test
    void matchAllQuery() {
        matchQueryService.matchAllQuery();
    }

    @Test
    void matchQuery() {
        matchQueryService.matchQuery();
    }

    @Test
    void matchMultiQuery() {
        matchQueryService.matchMultiQuery();
    }
}