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
        Result result = Result.error();
        result.setCode(5001);
        result.setMessage("接口暂不可用5001");
        return result;
    }
}
