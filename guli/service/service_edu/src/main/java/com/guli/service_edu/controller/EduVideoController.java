package com.guli.service_edu.controller;


import com.commonutils.Result;
import com.guli.service_edu.entity.EduVideo;
import com.guli.service_edu.service.EduVideoService;
import com.guli.service_edu.service.VodClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author 刘佳俊
 * @since 2022-01-11
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;
    @Autowired
    private VodClient vodClient;
    @PostMapping("/addVideo")
    public Result addVideo(@RequestBody EduVideo eduVideo){
        boolean save = eduVideoService.save(eduVideo);
        return Result.ok();
    }

    @GetMapping("/deleteVideo/{videoID}")
    public Result deleteVideo(@PathVariable("videoID") String id){
        EduVideo eduVideo = eduVideoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        if(!StringUtils.isEmpty(videoSourceId)){
            vodClient.removeVideo(videoSourceId);
        }
        boolean b = eduVideoService.removeById(id);
        return Result.ok();
    }

    @PostMapping("/updateVideo")
    public Result updateVideo(@RequestBody EduVideo eduVideo){
        boolean save = eduVideoService.updateById(eduVideo);
        return Result.ok();
    }
    @GetMapping("/getVideo/{videoID}")
    public Result getVideo(@PathVariable("videoID") String id){
        EduVideo eduVideo = eduVideoService.getById(id);
        return Result.ok().data("video",eduVideo);
    }



}

