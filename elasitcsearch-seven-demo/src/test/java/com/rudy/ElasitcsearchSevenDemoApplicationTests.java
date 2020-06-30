package com.rudy;

import com.rudy.entity.HotelResource;
import com.rudy.javaapi.IndexApiService;
import com.rudy.service.IHotelService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class ElasitcsearchSevenDemoApplicationTests {

	@Resource
	private IndexApiService service;

	@Resource
	private IHotelService iHotelService;

	@Test
	void contextLoads() {
		service.bulkDoc();
	}

	@Test
	void page() {
		List<HotelResource> hotelResources = iHotelService.pageQuery(0, 100);
		System.out.println(hotelResources.size());
	}

}
