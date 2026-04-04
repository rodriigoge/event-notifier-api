package com.event_notifier_api;

import org.springframework.boot.SpringApplication;

public class TestEventNotifierApiApplication {

	public static void main(String[] args) {
		SpringApplication.from(EventNotifierApiApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
