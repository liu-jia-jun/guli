package com.guli.service_edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.guli.service_edu.entity.EduChapter;
import com.guli.service_edu.entity.EduCourse;
import com.guli.service_edu.entity.EduCourseDescription;
import com.guli.service_edu.entity.vo.*;
import com.guli.service_edu.mapper.EduCourseMapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.service_edu.service.*;
import com.service_base.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        // 删除阿里云中的视频
        //根据id删除所有视频
        boolean remove = eduVideoService.removeVideoByCourseId(id);


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

    /**
     * 根据讲师id查询当前讲师的课程列表
     * @param teacherId
     * @return
     */
    @Override
    public List<EduCourse> selectByTeacherId(String teacherId) {

        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<EduCourse>();

        queryWrapper.eq("teacher_id", teacherId);
        //按照最后更新时间倒序排列
        queryWrapper.orderByDesc("gmt_modified");

        List<EduCourse> courses = baseMapper.selectList(queryWrapper);
        return courses;
    }

    @Override
    public Map<String, Object> pageListWeb(Page<EduCourse> pageParam, CourseQueryVo courseQuery) {

        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(courseQuery.getSubjectParentId())) {
            queryWrapper.eq("subject_parent_id", courseQuery.getSubjectParentId());
        }

        if (!StringUtils.isEmpty(courseQuery.getSubjectId())) {
            queryWrapper.eq("subject_id", courseQuery.getSubjectId());
        }

        if (!StringUtils.isEmpty(courseQuery.getBuyCountSort())) {
            queryWrapper.orderByDesc("buy_count");
        }

        if (!StringUtils.isEmpty(courseQuery.getGmtCreateSort())) {
            queryWrapper.orderByDesc("gmt_create");
        }

        if (!StringUtils.isEmpty(courseQuery.getPriceSort())) {
            queryWrapper.orderByDesc("price");
        }

        baseMapper.selectPage(pageParam, queryWrapper);

        List<EduCourse> records = pageParam.getRecords();
        long current = pageParam.getCurrent();
        long pages = pageParam.getPages();
        long size = pageParam.getSize();
        long total = pageParam.getTotal();
        boolean hasNext = pageParam.hasNext();
        boolean hasPrevious = pageParam.hasPrevious();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }

    @Override
    public void updatePageViewCount(String id) {
        EduCourse course = baseMapper.selectById(id);
        course.setViewCount(course.getViewCount() + 1);
        baseMapper.updateById(course);
    }
    @Override
    public CourseWebVo selectInfoWebById(String id) {
        this.updatePageViewCount(id);
        return baseMapper.selectInfoWebById(id);
    }


}
