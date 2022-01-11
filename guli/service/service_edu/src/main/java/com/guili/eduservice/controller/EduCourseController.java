package com.guili.eduservice.controller;


import com.commonutils.Result;
import com.guili.eduservice.entity.vo.CourseInfoForm;
import com.guili.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author 刘佳俊
 * @since 2022-01-11
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    // 添加课程基本信息的方法
    @PostMapping("/addCourseInfo")
    public Result addCourseInfo(@RequestBody CourseInfoForm courseInfoForm){

        String id = eduCourseService.saveCourseInfo(courseInfoForm);

        return Result.ok().data("id",id);
    }
}

