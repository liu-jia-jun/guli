package com.guli.service_edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.service_edu.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.service_edu.entity.vo.CourseInfoForm;
import com.guli.service_edu.entity.vo.CoursePublishVo;
import com.guli.service_edu.entity.vo.CourseQuery;

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
