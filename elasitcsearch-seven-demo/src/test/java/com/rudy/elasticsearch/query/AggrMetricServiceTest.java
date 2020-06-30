package com.rudy.elasticsearch.query;

import com.rudy.ElasitcsearchSevenDemoApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class AggrMetricServiceTest extends ElasitcsearchSevenDemoApplicationTests {

    @Autowired
    private AggrMetricService aggrMetricService;

    @Test
    void aggregationStats() {
        aggrMetricService.aggregationStats();
    }

    @Test
    void aggregationMin() {
        aggrMetricService.aggregationMin();
    }

    @Test
    void aggregationMax() {
        aggrMetricService.aggregationMax();
    }

    @Test
    void aggregationAvg() {
        aggrMetricService.aggregationAvg();
    }

    @Test
    void aggregationSum() {
        aggrMetricService.aggregationSum();
    }

    @Test
    void aggregationCount() {
        aggrMetricService.aggregationCount();
    }

    @Test
    void aggregationPercentiles() {
        aggrMetricService.aggregationPercentiles();
    }
}