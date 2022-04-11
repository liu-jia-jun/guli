package com.guli.service_edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.service_edu.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author 刘佳俊
 * @since 2021-12-16
 */
public interface EduTeacherService extends IService<EduTeacher> {

    Map<String, Object> pageListWeb(Page<EduTeacher> pageParam);
}
