package com.guli.service_order.client;

import com.commonutils.bean.CourseWebVoOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 刘佳俊
 */
@Component
@FeignClient("service-edu")
public interface EduClient {
    //根据课程id查询课程信息
    @GetMapping("getDto/{courseId}")
    public CourseWebVoOrder getCourseInfoDto(@PathVariable String courseId);
}
