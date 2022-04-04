package com.guli.service_edu.service;

import com.guli.service_edu.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.service_edu.entity.vo.SubjectTree;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author 刘佳俊
 * @since 2022-01-10
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file);

    List<SubjectTree> getAllOneTwoSubject();
}
