package com.rudy;

import com.rudy.javaapi.IndexApiService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ElasitcsearchSevenDemoApplicationTests {

	@Resource
	private IndexApiService service;

	@Test
	void contextLoads() {
		service.indexDocument();
	}

}
