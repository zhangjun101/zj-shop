package com.zj.orderManage;

import com.zj.result.Result;
import org.springframework.stereotype.Component;

/**
 * feign服务降级处理类
 */
@Component
public class OrderManageFeignFallback implements OrderManageFeignClient{

    @Override
    public Result getAllOrders() {
        Result result = new Result();
        result.setMsg("服务暂不可用");
        return result;
    }
}
