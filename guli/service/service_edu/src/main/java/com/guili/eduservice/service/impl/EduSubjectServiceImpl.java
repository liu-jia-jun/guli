package com.guili.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.guili.eduservice.entity.EduSubject;
import com.guili.eduservice.entity.excel.SubjectData;
import com.guili.eduservice.listener.SubjectExcelListener;
import com.guili.eduservice.mapper.EduSubjectMapper;
import com.guili.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author 刘佳俊
 * @since 2022-01-10
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file) {
        try {
            InputStream in = file.getInputStream();

            // 调用方法进行读取excel文件中的数据
            EasyExcel.read(in,SubjectData.class,new SubjectExcelListener()).sheet().doRead();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
