package com.guli.service_edu.mapper;

import com.guli.service_edu.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guli.service_edu.entity.vo.CoursePublishVo;
import com.guli.service_edu.entity.vo.CourseWebVo;
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


    CourseWebVo selectInfoWebById(String id);
}
