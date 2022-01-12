package com.guili.eduservice.service;

import com.guili.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guili.eduservice.entity.vo.CourseInfoForm;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 刘佳俊
 * @since 2022-01-11
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseInfo(String id);

    boolean updatCourseInfo(CourseInfoForm courseInfoForm);
}
