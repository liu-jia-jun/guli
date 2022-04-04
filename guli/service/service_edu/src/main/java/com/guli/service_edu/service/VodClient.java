package com.guli.service_edu.service;

import com.commonutils.Result;
import com.guli.service_edu.service.impl.VodFileDegradeFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 用于服务的远程调用
 */
@FeignClient(value = "service-vod",fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {
    @DeleteMapping("/eduvod/filevod/{videoId}")
    public Result removeVideo(@PathVariable("videoId") String videoId);



    @DeleteMapping(value = "/eduvod/filevod/delete-batch")
    public Result deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);
}
