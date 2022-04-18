package com.zj.orderManage;

import com.alibaba.fastjson.JSON;
import com.zj.result.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orderManage")
public class OrderManageController {

    @RequestMapping(value = "/getAllOrders",method = RequestMethod.GET)
    public Result getAllOrders() {
        List<OrderEntity> orderEntityList = new ArrayList<OrderEntity>();
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderName("订单1");
        orderEntityList.add(orderEntity);
        return Result.ok().data("data",JSON.toJSONString(orderEntityList));
    }
}