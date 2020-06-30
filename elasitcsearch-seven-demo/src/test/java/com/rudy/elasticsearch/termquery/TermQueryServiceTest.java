package com.rudy.elasticsearch.termquery;

import com.rudy.ElasitcsearchSevenDemoApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class TermQueryServiceTest extends ElasitcsearchSevenDemoApplicationTests {

    @Autowired
    private TermQueryService termQueryService;

    @Test
    void termQuery() {
        termQueryService.termQuery();
    }
}