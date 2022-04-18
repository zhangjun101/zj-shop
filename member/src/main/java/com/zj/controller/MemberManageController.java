//package com.zj.controller;
////import com.zj.result.Result;
//import com.zj.result.Result;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import javax.annotation.Resource;
//
//
//
//@RestController
//@RequestMapping("/memberManage")
//public class MemberManageController {
//
//    @Resource
//    private RestTemplate restTemplate;
//
//
//    /**
//     * 学习测试.
//     * 调用其他服务 采用ribbon负载均衡 resttmplate+@LoadBalanced 方式
//     */
//    @RequestMapping(value = "/linkGoodsTest",method = RequestMethod.GET)
//    public Result linkGoodsTest(){
//        String goodsServerName = "goods-server";
//        Result result = restTemplate.getForObject("http://"+goodsServerName + "/goodsManage/getAllGoods", Result.class);
//        result.setMessage("联调Coods-server ok");
//        return result;
//    }
//
//
//}
