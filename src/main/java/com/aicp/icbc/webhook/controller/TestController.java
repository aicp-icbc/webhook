package com.aicp.icbc.webhook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Description:
 * @Author: 吴开云
 * @Date: 2019/8/16 0016
 * @Version： 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/time")
    public String test(){
        return " test success ~~ " + LocalDateTime.now();
    }
}
