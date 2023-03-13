package com.checkoutabandonment.recoveryplugin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CheckoutRecoveryPluginApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheckoutRecoveryPluginApplication.class, args);
	}

}
