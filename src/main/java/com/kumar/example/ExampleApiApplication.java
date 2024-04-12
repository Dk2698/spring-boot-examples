package com.kumar.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableTransactionManagement
public class ExampleApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleApiApplication.class, args);
	}

//	@Bean
//	public PlatformTransactionManager platformTransactionManager(MongoDatabaseFactory dbFactor){
//		return new MongoTransactionManager(dbFactor);
//	}

}
