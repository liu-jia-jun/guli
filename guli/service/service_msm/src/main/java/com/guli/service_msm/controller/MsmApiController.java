package com.guli.service_msm.controller;

import com.commonutils.RandomUtil;
import com.commonutils.Result;
import com.guli.service_msm.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 刘佳俊
 */
@RestController
@RequestMapping("/api/msm")
@CrossOrigin //跨域
public class MsmApiController {


    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping(value = "/send/{email}")
    public Result code(@PathVariable("email") String email) {
        String code = redisTemplate.opsForValue().get(email);
        if(!StringUtils.isEmpty(code)) {
            return Result.ok();
        }

        code = RandomUtil.getFourBitRandom();
        Map<String,Object> param = new HashMap<>();
        param.put("code", code);

        boolean isSend = msmService.send(email, param);
        if(isSend) {
            redisTemplate.opsForValue().set(email, code,5, TimeUnit.MINUTES);
            return Result.ok();
        } else {
            return Result.error().message("发送邮件失败");
        }
    }
}