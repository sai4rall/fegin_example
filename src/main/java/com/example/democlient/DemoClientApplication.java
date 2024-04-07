package com.example.democlient;

import feign.Feign;
import feign.Retryer;
import feign.Target;
import feign.micrometer.MicrometerCapability;
import feign.okhttp.OkHttpClient;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoClientApplication.class, args);
	}

@Autowired
MeterRegistry meterRegistry;
	@Bean
	public UserClient getUserClient(){
		return buildFeignCLient(UserClient.class,meterRegistry);
	}


	private UserClient buildFeignCLient(Class<UserClient> userClientClass, MeterRegistry metricRegistry) {
	return Feign.builder()
			.client(new OkHttpClient())
			.retryer(new Retryer.Default(200,299,1))
			.addCapability(new MicrometerCapability(metricRegistry))
//			.target(Target.EmptyTarget.create(userClientClass));
			.target(UserClient.class, "http://sai.com/ss");

	}
}
