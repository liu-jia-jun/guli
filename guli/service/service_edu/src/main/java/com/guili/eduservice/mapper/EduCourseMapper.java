package com.guili.eduservice.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guili.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guili.eduservice.entity.vo.CoursePublishVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author 刘佳俊
 * @since 2022-01-11
 */
@Mapper
public interface EduCourseMapper extends BaseMapper<EduCourse> {


     CoursePublishVo getCoursePublishVoById(String id);



}
