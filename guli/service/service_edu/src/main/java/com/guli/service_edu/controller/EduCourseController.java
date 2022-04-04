package com.guli.service_edu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.commonutils.Result;
import com.guli.service_edu.entity.EduCourse;
import com.guli.service_edu.entity.vo.CourseInfoForm;
import com.guli.service_edu.entity.vo.CoursePublishVo;
import com.guli.service_edu.entity.vo.CourseQuery;
import com.guli.service_edu.service.EduCourseService;
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
@RequestMapping("/eduservice/course")
@CrossOrigin// 用于解决跨域问题
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    // 添加课程基本信息的方法
    @PostMapping("/addCourseInfo")
    public Result addCourseInfo(@RequestBody CourseInfoForm courseInfoForm) {

        String id = eduCourseService.saveCourseInfo(courseInfoForm);

        return Result.ok().data("id", id);
    }

    @GetMapping("/getCourseInfo/{courseId}")
    public Result getCourseByID(@PathVariable("courseId") String id) {

        CourseInfoForm courseInfoForm = eduCourseService.getCourseInfo(id);

        return Result.ok().data("courseInfo", courseInfoForm);
    }

    @PostMapping("/updateCourseInfo")
    public Result updateCourseInfo(@RequestBody CourseInfoForm courseInfoForm) {
        boolean b = eduCourseService.updatCourseInfo(courseInfoForm);
        return b ? Result.ok().data("msg", "修改成功") : Result.error().data("msg", "修改失败");
    }

    @ApiOperation(value = "根据ID获取课程发布信息")
    @GetMapping("course-publish-info/{id}")
    public Result getCoursePublishVoById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id) {
        CoursePublishVo courseInfoForm = eduCourseService.getCoursePublishVoById(id);
        return Result.ok().data("coursePublish", courseInfoForm);
    }

    // 课程的最终发布
    @PostMapping("/publishCourse/{id}")
    public Result publicCourse(@PathVariable("id") String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");
        eduCourseService.updateById(eduCourse);
        return Result.ok();
    }


    // 课程列表
    @GetMapping("/getAllCourse")
    public Result getAllCourse() {
        List<EduCourse> courseList = eduCourseService.getAllCourse();

        return Result.ok().data("list", courseList);
    }

    // 课程列表分页查询
    @GetMapping("/getPageListCourse/{current}/{limit}")
    public Result getPageListCourse(@PathVariable("current") long current,
                                    @PathVariable("limit") long limit) {
        Page<EduCourse> eduCoursePage = eduCourseService.getPageListCourse(current, limit);
        List<EduCourse> list = eduCoursePage.getRecords();
        long current1 = eduCoursePage.getCurrent();
        long total = eduCoursePage.getTotal();
        return Result.ok().data("list", list).data("current",current1).data("total",total);
    }

    // 课程列表的条件查询
    @PostMapping("/querySelectCourse/{current}/{limit}")
    public Result querySelectCourse(@RequestBody CourseQuery courseQuery,
                                    @PathVariable("current") long current,
                                    @PathVariable("limit") long limit) {
        Page<EduCourse> eduCoursesPage = eduCourseService.querySelectCourse(current,limit,courseQuery);
        List<EduCourse> eduCourse = eduCoursesPage.getRecords();
        long current1 = eduCoursesPage.getCurrent();
        long total = eduCoursesPage.getTotal();
        return Result.ok().data("list", eduCourse).data("current",current1).data("total",total);
    }


    @DeleteMapping("/deleteCourse/{courseId}")
    public Result deleteCourse(@PathVariable("courseId") String id){
        eduCourseService.deleteCourseById(id);
        return Result.ok();
    }
}

