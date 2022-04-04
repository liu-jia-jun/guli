package com.guli.service_edu.service;

import com.guli.service_edu.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author 刘佳俊
 * @since 2022-01-11
 */
public interface EduVideoService extends IService<EduVideo> {
    boolean removeVideoByCourseId(String courseId);
}
