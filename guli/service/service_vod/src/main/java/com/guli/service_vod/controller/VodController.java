package com.guli.service_vod.controller;


import com.commonutils.Result;
import com.guli.service_vod.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 刘佳俊
 */
@RestController
@CrossOrigin
@RequestMapping("/eduvod/filevod")
@Api(description = "阿里云视频点播微服务")
public class VodController {
    @Autowired
    private VideoService videoService;

    @PostMapping("upload")
    public Result uploadVideo(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) throws Exception {

        String videoId = videoService.uploadVideo(file);
        return Result.ok().message("视频上传成功").data("videoId", videoId);
    }

    @DeleteMapping("{videoId}")
    public Result removeVideo(@ApiParam(name = "videoId", value = "云端视频id", required = true)
                              @PathVariable String videoId) {

        videoService.removeVideo(videoId);
        return Result.ok().message("视频删除成功");
    }

    /**
     * 删除课程时删除课程下的所有的视频
     * @param videoIdList
     * @return
     */
    @DeleteMapping("delete-batch")
    public Result removeVideoList(@ApiParam(name = "videoIdList", value = "云端视频ids", required = true)
                                  @RequestParam("videoIdList") List<String> videoIdList) {

        videoService.removeVideoList(videoIdList);
        return Result.ok().message("视频删除成功");

    }


}
