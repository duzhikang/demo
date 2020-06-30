package com.rudy.elasticsearch.query;

import com.rudy.ElasitcsearchSevenDemoApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class WildcardQueryServiceTest extends ElasitcsearchSevenDemoApplicationTests {

    @Autowired
    private WildcardQueryService wildcardQueryService;

    @Test
    void wildcardQuery() {
        wildcardQueryService.wildcardQuery();
    }
}