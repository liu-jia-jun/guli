package com.guli.service_edu.mapper;

import com.guli.service_edu.entity.EduTeacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 讲师 Mapper 接口
 * </p>
 *
 * @author 刘佳俊
 * @since 2021-12-16
 */
@Mapper
public interface EduTeacherMapper extends BaseMapper<EduTeacher> {

    public String selectTeacherNameById(@Param("teacherId") String teacherId);
}
