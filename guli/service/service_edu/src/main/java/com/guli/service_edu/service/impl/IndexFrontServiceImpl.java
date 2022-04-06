package com.guli.service_edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.service_edu.entity.EduCourse;
import com.guli.service_edu.entity.EduTeacher;
import com.guli.service_edu.service.EduCourseService;
import com.guli.service_edu.service.EduTeacherService;
import com.guli.service_edu.service.IndexFrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘佳俊
 */
@Service
public class IndexFrontServiceImpl implements IndexFrontService {
    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduTeacherService teacherService;

    @Override
    @Cacheable(value = "index" ,key = "'courseList'")
    public List<EduCourse> getCourseList() {
        //查询前8条热门课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 8");
        List<EduCourse> courseList = courseService.list(wrapper);
        return courseList;
    }

    @Override
    @Cacheable(value = "index" ,key = "'teacherList'")
    public List<EduTeacher> getTeacherList() {
        //查询前4条名师
        QueryWrapper<EduTeacher> wrapperTeacher = new QueryWrapper<>();
        wrapperTeacher.orderByDesc("id");
        wrapperTeacher.last("limit 4");
        List<EduTeacher> teacherList = teacherService.list(wrapperTeacher);
        return teacherList;
    }
}
