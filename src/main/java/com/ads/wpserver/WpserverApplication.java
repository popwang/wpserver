package com.ads.wpserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.ads.wpserver.netty.WpNettyServer;

@SpringBootApplication
@EnableAutoConfiguration
@MapperScan("com.ads.wpserver.mysql.mapper")
public class WpserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(WpserverApplication.class, args);
	}
	
	@Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            WpNettyServer server = (WpNettyServer)ctx.getBean("wpNettyServer");
            server.starter();
        };
    }
}
