package com.lobe2.tpslaiya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class TpslaiyaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpslaiyaApplication.class, args);
	}
}
