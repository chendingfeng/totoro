package com.dingfeng.totoro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan("com.dingfeng.totoro.mapper")
@EnableDiscoveryClient
@SpringBootApplication
public class TotoroSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(TotoroSecurityApplication.class, args);
    }


}
