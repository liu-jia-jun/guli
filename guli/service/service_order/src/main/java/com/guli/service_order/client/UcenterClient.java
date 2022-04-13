package com.guli.service_order.client;

import com.commonutils.bean.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author 刘佳俊
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    //根据课程id查询课程信息
    @PostMapping("/ucenterservice/apimember/getInfoUc/{id}")
    public UcenterMemberOrder getInfo(@PathVariable("id") String id);
}
