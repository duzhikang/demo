package com.rudy.elasticsearch.query;

import com.rudy.ElasitcsearchSevenDemoApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class TermQueryServiceTest extends ElasitcsearchSevenDemoApplicationTests {

    @Autowired
    private TermQueryService termQueryService;

    @Test
    void termQuery() {
        termQueryService.termQuery();
    }

    @Test
    void termsQuery() {
        termQueryService.termsQuery();
    }
}