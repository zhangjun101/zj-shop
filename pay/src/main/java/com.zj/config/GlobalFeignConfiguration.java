package com.zj.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @author zby
 * @title: GlobalFeignConfiguration
 * @projectName zj-shop
 * @description: 定义全局Feign的日志级别
 * @date 2022/4/159:01
 */
public class GlobalFeignConfiguration {

    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

}
