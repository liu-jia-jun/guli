package com.guli.service_edu.service.impl;

import com.commonutils.Result;
import com.guli.service_edu.service.VodClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 刘佳俊
 */
@Component
public class VodFileDegradeFeignClient implements VodClient {
    @Override
    public Result removeVideo(String videoId) {
        return Result.error().message("视频删除失败time out");
    }

    @Override
    public Result deleteBatch(List<String> videoIdList) {
        return Result.error().message("删除多个视频失败time out");
    }
}
