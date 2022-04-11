package com.guli.service_ucenter.controller;


import com.commonutils.JwtUtils;
import com.commonutils.Result;
import com.guli.service_ucenter.entity.UcenterMember;
import com.guli.service_ucenter.entity.vo.LoginVo;
import com.guli.service_ucenter.entity.vo.RegisterVo;
import com.guli.service_ucenter.service.UcenterMemberService;
import com.service_base.exceptionhandler.GuliException;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.sun.webkit.perf.WCFontPerfLogger.log;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author 刘佳俊
 * @since 2022-04-06
 */
@RestController
@RequestMapping("/ucenterservice/apimember")
@CrossOrigin
@Log4j2
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;



    @ApiOperation(value = "会员登录")
    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo) {
        String token = memberService.login(loginVo);
        System.out.println("-----------------"+token);
        return Result.ok().data("token", token);
    }

    @ApiOperation(value = "会员注册")
    @PostMapping("register")
    public Result register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return Result.ok();
    }
    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("auth/getLoginInfo")
    public Result getLoginInfo(HttpServletRequest request){
        try {
            System.out.println("+++++++++++++++:"+request.getHeader("token"));
            String memberId = JwtUtils.getMemberIdByJwtToken(request);
            System.out.println(memberId);
            UcenterMember member = memberService.getLoginInfo(memberId);
            return Result.ok().data("item", member);
        }catch (Exception e){
            e.printStackTrace();
            throw new GuliException(20001,"error");
        }
    }
}

