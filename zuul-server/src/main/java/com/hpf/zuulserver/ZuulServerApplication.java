package com.hpf.zuulserver;

import com.hpf.zuulserver.Filter.PreRequestLogFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class ZuulServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulServerApplication.class, args);
    }


//    @Bean
//    public PreRequestLogFilter preRequestLogFilter(){
//        return new PreRequestLogFilter();
//    }

}
