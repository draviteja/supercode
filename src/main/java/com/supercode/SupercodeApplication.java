package com.supercode;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimeZone;

@EnableAsync
@SpringBootApplication
@EnableCaching
public class SupercodeApplication implements ApplicationRunner, InitializingBean {

	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		System.out.println("===========supercode init method=============");
	}

	public static void main(String[] args) {
		//SpringApplication.run(SupercodeApplication.class, args);

		SpringApplication app = new SpringApplication(SupercodeApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);

	}

	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		System.out.println("==============Hello World from Application Runner=====");
	}

	@PreDestroy
	void destroy() {
		System.out.println("===========supercode destroy method=============");
	}

	@Override
	public void afterPropertiesSet(){
		System.out.println("===============supercode afterPropertiesSet=================");
	}

}
