package com.shop.nearby.nearbyshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.shop.nearby.nearbyshop.dao")
@SpringBootApplication
public class NearbyshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(NearbyshopApplication.class, args);
    }

}
