package com.guili.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guili.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guili.eduservice.entity.vo.CourseInfoForm;
import com.guili.eduservice.entity.vo.CoursePublishVo;
import com.guili.eduservice.entity.vo.CourseQuery;

import java.util.List;

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

    CoursePublishVo getCoursePublishVoById(String id);

    List<EduCourse> getAllCourse();

    Page<EduCourse> querySelectCourse(Long current, Long limit, CourseQuery courseQuery);

    Page<EduCourse> getPageListCourse(Long current, Long limit);


    boolean deleteCourseById(String id);
}
