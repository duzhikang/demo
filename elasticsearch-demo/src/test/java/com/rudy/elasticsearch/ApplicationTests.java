package com.rudy.elasticsearch;

import com.rudy.elasticsearch.javaapi.IndexApiService;
import com.rudy.elasticsearch.service.IHotelService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ApplicationTests {

	@Resource
	private IndexApiService indexApiService;

	@Resource
	private IHotelService hotelService;

	@Test
	void indexDocument() {
		indexApiService.indexDocument();
	}

	@Test
	void count() {
		hotelService.count();
		System.out.println(hotelService.pageQuery(0, 10));
	}

}
