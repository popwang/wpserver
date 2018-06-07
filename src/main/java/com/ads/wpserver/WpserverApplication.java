package com.ads.wpserver;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.ads.wpserver.server.WpNettyServer;

@SpringBootApplication
@EnableAutoConfiguration
public class WpserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(WpserverApplication.class, args);
	}
	
	@Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("Let's inspect the beans provided by Spring Boot:");
            
            WpNettyServer server = (WpNettyServer)ctx.getBean("wpNettyServer");
            server.starter();
        };
    }
}
