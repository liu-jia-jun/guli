package com.guili.eduservice.service.impl;

import com.guili.eduservice.entity.EduCourse;
import com.guili.eduservice.entity.EduCourseDescription;
import com.guili.eduservice.entity.vo.CourseInfoForm;
import com.guili.eduservice.mapper.EduCourseMapper;
import com.guili.eduservice.service.EduCourseDescriptionService;
import com.guili.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.service_base.exceptionhandler.GuliException;
import org.apache.poi.ss.formula.functions.BaseNumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

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
    EduCourseDescriptionService descriptionService;

    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm,eduCourse);

        int insert = baseMapper.insert(eduCourse);
        if(insert<1){
            throw new GuliException(20001, "课程信息保存失败");
        }
        EduCourseDescription eduCourseDescription = new EduCourseDescription();

        String id = eduCourse.getId();

        BeanUtils.copyProperties(courseInfoForm,eduCourseDescription);
        eduCourseDescription.setId(id);

        boolean save = descriptionService.save(eduCourseDescription);
        if(!save){
            throw new GuliException(20001, "课程详情信息保存失败");
        }
        return id;
    }

}
