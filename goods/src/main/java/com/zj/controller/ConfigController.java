package com.zj.controller;

import com.zj.entity.GoodsEntity;
import com.zj.result.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zby
 * @title: ConfigController
 * @projectName zj-shop
 * @description: TODO
 * @date 2022/4/199:22
 */
@RestController
@RefreshScope //开启更新功能
@RequestMapping("/config")
public class ConfigController {

    @Value("${name}")
    private String name;

    @RequestMapping(value = "/getConfigWithName",method = RequestMethod.GET)
    public String getAllGoods(){
        return name;
    }

}
