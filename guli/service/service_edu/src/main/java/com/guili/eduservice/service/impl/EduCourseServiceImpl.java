package com.guili.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guili.eduservice.entity.*;
import com.guili.eduservice.entity.vo.CourseInfoForm;
import com.guili.eduservice.entity.vo.CoursePublishVo;
import com.guili.eduservice.entity.vo.CourseQuery;
import com.guili.eduservice.mapper.EduCourseMapper;
import com.guili.eduservice.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.service_base.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 刘佳俊
 * @since 2022-01-11
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {


    @Autowired
    private EduCourseDescriptionService descriptionService;
    @Autowired
    private EduCourseMapper eduCourseMapper;

    @Autowired
    private EduVideoService eduVideoService;
    @Autowired
    private EduChapterService eduChapterService;
    @Autowired
    private EduSubjectService eduSubjectService;


    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm, eduCourse);

        int insert = baseMapper.insert(eduCourse);
        if (insert < 1) {
            throw new GuliException(20001, "课程信息保存失败");
        }
        EduCourseDescription eduCourseDescription = new EduCourseDescription();

        String id = eduCourse.getId();

        BeanUtils.copyProperties(courseInfoForm, eduCourseDescription);
        eduCourseDescription.setId(id);

        boolean save = descriptionService.save(eduCourseDescription);
        if (!save) {
            throw new GuliException(20001, "课程详情信息保存失败");
        }
        return id;
    }

    @Override
    public CourseInfoForm getCourseInfo(String id) {
        EduCourse eduCourse = baseMapper.selectById(id);

        QueryWrapper<EduCourseDescription> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        EduCourseDescription description = descriptionService.getOne(wrapper);

        CourseInfoForm courseInfoForm = new CourseInfoForm();

        BeanUtils.copyProperties(eduCourse, courseInfoForm);
        BeanUtils.copyProperties(description, courseInfoForm);

        return courseInfoForm;

    }

    @Override

    public boolean updatCourseInfo(CourseInfoForm courseInfoForm) {

        EduCourse eduCourse = new EduCourse();
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoForm, eduCourse);
        BeanUtils.copyProperties(courseInfoForm, eduCourseDescription);

        int i = baseMapper.updateById(eduCourse);

        boolean b = descriptionService.updateById(eduCourseDescription);

        b = i > 0 && b == true ? true : false;

        return b;
    }


    @Override
    public CoursePublishVo getCoursePublishVoById(String id) {
        return eduCourseMapper.getCoursePublishVoById(id);
    }

    @Override
    public List<EduCourse> getAllCourse() {
        return baseMapper.selectList(null);
    }

    @Override
    public Page<EduCourse> querySelectCourse(Long current, Long limit, CourseQuery courseQuery) {
        Page<EduCourse> eduCoursePage = new Page<>(current, limit);
        QueryWrapper<EduCourse> wrapper = new QueryWrapper();
        String subjectId = courseQuery.getSubjectId();
        String teacherId = courseQuery.getTeacherId();
        String title = courseQuery.getTitle();
        String subjectParentId = courseQuery.getSubjectParentId();
        if (!StringUtils.isEmpty(subjectParentId)) {
            wrapper.eq("subject_parent_id", subjectParentId);
        }
        if (!StringUtils.isEmpty(title)) {
            wrapper.eq("title", title);
        }
        if (!StringUtils.isEmpty(teacherId)) {
            wrapper.eq("teacher_id", teacherId);
        }
        if (!StringUtils.isEmpty(subjectId)) {
            wrapper.eq("subject_id", subjectId);
        }
        baseMapper.selectPage(eduCoursePage, wrapper);
        return eduCoursePage;


    }

    @Override
    public Page<EduCourse> getPageListCourse(Long current, Long limit) {
        Page<EduCourse> eduCoursePage = new Page<>(current, limit);
        this.page(eduCoursePage, null);
        return eduCoursePage;

    }

    @Override
    public boolean deleteCourseById(String id) {
        // video
        QueryWrapper<EduVideo> wrapper = new QueryWrapper();
        wrapper.eq("course_id", id);
        boolean remove = eduVideoService.remove(wrapper);
        // chapter
        QueryWrapper<EduChapter> wrapper1 = new QueryWrapper();
        wrapper1.eq("course_id", id);
        boolean remove1 = eduChapterService.remove(wrapper1);


        // description
        boolean remove3 = descriptionService.removeById(id);
        // course
        boolean b = this.removeById(id);

        return remove & remove1 & remove3 & b;

    }

}
