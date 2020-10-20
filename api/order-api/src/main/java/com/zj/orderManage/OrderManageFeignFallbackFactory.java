package com.zj.orderManage;


import com.zj.result.Result;
import feign.hystrix.FallbackFactory;

/**
 * 服务调用降级处理工厂
 */
public class OrderManageFeignFallbackFactory implements FallbackFactory<OrderManageFeignClient> {

    @Override
    public OrderManageFeignClient create(Throwable throwable) {

        return new OrderManageFeignClient() {
            @Override
            public Result getAllOrders() {
                Result result = new Result();
                result.setMsg("服务暂不可用");
                return result;
            }
        };

    }
}
