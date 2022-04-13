package com.guli.service_edu.service.impl;


import com.commonutils.bean.UcenterMemberCommentVo;
import com.guli.service_edu.service.UcenterClient;


import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 刘佳俊
 */
@Component
public class UcenterClientImpl implements UcenterClient {
    @Override
    public UcenterMemberCommentVo getInfo(@PathVariable String id) {
        System.out.println("用户评论，服务调用有问题");
        return null;
    }
}