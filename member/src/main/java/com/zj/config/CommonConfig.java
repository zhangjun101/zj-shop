//package com.zj.config;
//
//
//import com.netflix.loadbalancer.IRule;
//import com.netflix.loadbalancer.RandomRule;
//import com.netflix.loadbalancer.RetryRule;
//import com.netflix.loadbalancer.RoundRobinRule;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
//import org.springframework.cloud.netflix.ribbon.RibbonClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.client.RestTemplate;
//
//
//@Configuration
//public class CommonConfig {
//
//    /**
//     * restTemplate 并且利用Ribbon的的负载均衡功能
//     * @return
//     */
//    @Bean
//    @LoadBalanced
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
//
//    /**
//     * 负载均衡策略选择
//     * @return
//     */
//
//    @Bean
//    public IRule myRule(){
//        return new RoundRobinRule();  //在这里选择负载均衡算法
//    }
//
//
//    public Boolean test(String a,Boolean b){
//        return  b;
//    }
//
//
//
//}
