package com.guili.eduservice.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.commonutils.Result;

import com.guili.eduservice.entity.vo.ChapterVo;
import com.guili.eduservice.entity.vo.CourseInfoForm;
import com.guili.eduservice.service.EduChapterService;
import com.guili.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author 刘佳俊
 * @since 2022-01-11
 */
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
@Api(description="课程章节管理")
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;

    @ApiOperation(value = "嵌套章节数据列表")
    @GetMapping("nested-list/{courseId}")
    public Result nestedListByCourseId(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId){

        List<ChapterVo> chapterVoList = eduChapterService.nestedList(courseId);
        return Result.ok().data("items", chapterVoList);
    }
}

