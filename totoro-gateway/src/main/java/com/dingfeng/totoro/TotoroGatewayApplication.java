package com.dingfeng.totoro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class TotoroGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(TotoroGatewayApplication.class, args);
    }
}
