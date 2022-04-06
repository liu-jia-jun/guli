package com.guli.service_edu.controller.front;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.commonutils.Result;
import com.guli.service_edu.entity.EduCourse;
import com.guli.service_edu.entity.EduTeacher;
import com.guli.service_edu.service.EduCourseService;
import com.guli.service_edu.service.EduTeacherService;
import com.guli.service_edu.service.IndexFrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 刘佳俊
 */
@RestController
@RequestMapping("/eduservice/indexfront")
@CrossOrigin
public class IndexController {


    @Autowired
    private IndexFrontService indexFrontService;

    //查询前8条热门课程，查询前4条名师
    @GetMapping("index")
    public Result index() {


        List<EduCourse> courseList = indexFrontService.getCourseList();


        List<EduTeacher> teacherList=indexFrontService.getTeacherList();



        return Result.ok().data("eduList",courseList).data("teacherList",teacherList);
    }
}
