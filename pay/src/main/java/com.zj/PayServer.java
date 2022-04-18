package com.zj;

import com.zj.config.GlobalFeignConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(defaultConfiguration = GlobalFeignConfiguration.class)
//@EnableHystrix //开启Hystrix支持
public class PayServer {
	public static void main(String[] args) {
		SpringApplication.run(PayServer.class, args);
	}
}
