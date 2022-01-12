package com.guili.eduservice.controller;


import com.commonutils.Result;
import com.guili.eduservice.entity.EduVideo;
import com.guili.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping("/addVideo")
    public Result addVideo(@RequestBody EduVideo eduVideo){
        boolean save = eduVideoService.save(eduVideo);
        return Result.ok();
    }

    @GetMapping("/deleteVideo/{videoID}")
    public Result deleteVideo(@PathVariable("videoID") String id){
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

