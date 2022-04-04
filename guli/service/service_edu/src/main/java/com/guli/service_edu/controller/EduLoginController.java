package com.guli.service_edu.controller;

import com.commonutils.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @author 刘佳俊
 */
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin// 用于解决跨域问题
public class EduLoginController {



    // login
    @PostMapping("/login")
    public Result login(){
        return Result.ok().data("token","admin");
    }
    // info
    @GetMapping("/info")
    public Result info(){
        return Result.ok().data("name","admin").data("roles","[admin]").data("avatar","https://images.unsplash.com/photo-1640367169401-534dec442631?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwxM3x8fGVufDB8fHx8&auto=format&fit=crop&w=500&q=60");
    }
}
