package com.zj.payManage;

import com.zj.orderManage.OrderManageFeignClient;
import com.zj.result.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/payManage")
public class PayManageController {

    @Resource
    private OrderManageFeignClient orderManageFeignClient;

    @RequestMapping(value = "/getAllPays",method = RequestMethod.GET)
    public Result getAllPays() {
        List<PayEntity> payEntityList = new ArrayList<PayEntity>();
        PayEntity payEntity = new PayEntity();
        payEntity.setPayName("第一个支付");
        payEntityList.add(payEntity);
        Result result=new Result();
        result.setData(payEntityList);
        return result;
    }


    @RequestMapping(value = "/linkedOrder",method = RequestMethod.GET)
    public Result linkedOrder() {
        Result allOrders = orderManageFeignClient.getAllOrders();
        return allOrders;
    }

}