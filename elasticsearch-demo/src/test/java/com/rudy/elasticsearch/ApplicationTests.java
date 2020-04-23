package com.rudy.elasticsearch;

import com.rudy.elasticsearch.javaapi.IndexApiService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ApplicationTests {

	@Resource
	private IndexApiService indexApiService;

	@Test
	void indexDocument() {
		indexApiService.indexDocument();
	}

}
