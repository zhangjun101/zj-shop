package com.zj.healthy;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class HealthyController {
    @RequestMapping(value="/healthyTest",method = RequestMethod.GET)
    public String healthyTest(){
        return "ok";
    }
}
