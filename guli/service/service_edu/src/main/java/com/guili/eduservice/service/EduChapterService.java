package com.guili.eduservice.service;

import com.guili.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guili.eduservice.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 刘佳俊
 * @since 2022-01-11
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> nestedList(String courseId);
}
