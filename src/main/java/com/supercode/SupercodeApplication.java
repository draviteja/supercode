package com.supercode;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.TimeZone;

@SpringBootApplication
@EnableCaching
public class SupercodeApplication implements ApplicationRunner {


	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public static void main(String[] args) {
		SpringApplication.run(SupercodeApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		System.out.println("==============Hello World from Application Runner=====");
	}

	@PreDestroy
	void destroy() {
		System.out.println("=============destroy=====");

		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
}
