package com.rudy.elasticsearch.query;

import com.rudy.ElasitcsearchSevenDemoApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class FuzzyQueryServiceTest extends ElasitcsearchSevenDemoApplicationTests {

    @Autowired
    private FuzzyQueryService fuzzyQueryService;

    @Test
    void fuzzyQuery() {
        fuzzyQueryService.fuzzyQuery();
    }
}