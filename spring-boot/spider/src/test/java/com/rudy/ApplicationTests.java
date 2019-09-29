package com.rudy;

import com.rudy.spider.SpiderCommon;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	private SpiderCommon common;

	@Before
	public void init() {
		common = new SpiderCommon();
	}

	@Test
	public void githubdemo() {
		common.githubspider();
	}

}
