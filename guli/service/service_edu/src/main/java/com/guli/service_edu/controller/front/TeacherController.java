package com.guli.service_edu.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.commonutils.Result;
import com.guli.service_edu.entity.EduCourse;
import com.guli.service_edu.entity.EduTeacher;
import com.guli.service_edu.service.EduCourseService;
import com.guli.service_edu.service.EduTeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 刘佳俊
 */
@RestController
@RequestMapping("/eduservice/teacherfront")
@CrossOrigin
public class TeacherController {
    @Autowired
    private EduTeacherService teacherService;

    @Autowired
    private EduCourseService courseService;


    @ApiOperation(value = "分页讲师列表")
    @GetMapping(value = "{page}/{limit}")
    public Result pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit){

        Page<EduTeacher> pageParam = new Page<EduTeacher>(page, limit);

        Map<String, Object> map = teacherService.pageListWeb(pageParam);

        return  Result.ok().data(map);
    }
    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping(value = "{id}")
    public Result getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){

        //查询讲师信息
        EduTeacher teacher = teacherService.getById(id);

        //根据讲师id查询这个讲师的课程列表
        List<EduCourse> courseList = courseService.selectByTeacherId(id);

        return Result.ok().data("teacher", teacher).data("courseList", courseList);
    }
}
