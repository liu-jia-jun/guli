package com.atguigu.eduorder.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 刘佳俊
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
