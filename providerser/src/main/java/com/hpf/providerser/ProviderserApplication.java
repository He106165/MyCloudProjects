package com.hpf.providerser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProviderserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderserApplication.class, args);
    }

}
