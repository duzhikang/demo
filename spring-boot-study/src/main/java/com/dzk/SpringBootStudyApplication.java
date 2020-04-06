package com.dzk;

import com.dzk.annotation.EnableUserClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

//@EnableUserClient
@SpringBootApplication
@ServletComponentScan(basePackages = "com.dzk.web.servlet")
public class SpringBootStudyApplication {

	public static void main(String[] args) {
		System.setProperty("env", "DEV");
		SpringApplication.run(SpringBootStudyApplication.class, args);
	}

}
