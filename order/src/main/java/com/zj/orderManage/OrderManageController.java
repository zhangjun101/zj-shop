package com.zj.orderManage;

import com.zj.result.Result;
import org.aspectj.weaver.ast.Or;
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
        orderEntity.setOrderName("第一个订单");
        orderEntityList.add(orderEntity);
        Result result=new Result();
        result.setData(orderEntityList);
        return result;
    }
}