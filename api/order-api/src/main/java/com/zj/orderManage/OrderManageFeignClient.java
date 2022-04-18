package com.zj.orderManage;

import com.zj.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @FeginClient
 * name：服务提供者的名称
 * 声明需要调用的微服务名称
 */
@FeignClient(value = "order-server",fallback = OrderManageFeignFallback.class)
public interface OrderManageFeignClient {

    @RequestMapping(value = "/orderManage/getAllOrders",method = RequestMethod.GET)
    public Result getAllOrders();

}