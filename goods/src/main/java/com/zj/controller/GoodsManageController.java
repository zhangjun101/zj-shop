package com.zj.controller;


import com.zj.entity.GoodsEntity;
import com.zj.result.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/goodsManage")
public class GoodsManageController {

    //@Value("${name}")
    private String name;

    @RequestMapping(value = "/getAllGoods",method = RequestMethod.GET)
    public Result getAllGoods(){
        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setGoodsName("第一件商品");
        List<GoodsEntity> goodsEntityList = new ArrayList<GoodsEntity>();
        goodsEntityList.add(goodsEntity);
        Result result=Result.ok().data("data",goodsEntityList);
        return result;
    }

}
