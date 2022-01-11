package com.guili.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guili.eduservice.entity.EduSubject;
import com.guili.eduservice.entity.excel.SubjectData;
import com.guili.eduservice.entity.vo.SubjectTree;
import com.guili.eduservice.listener.SubjectExcelListener;
import com.guili.eduservice.mapper.EduSubjectMapper;
import com.guili.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    EduSubjectService eduSubjectService;

    @Override
    public void saveSubject(MultipartFile file) {
        try {
            InputStream in = file.getInputStream();

            // 调用方法进行读取excel文件中的数据
            EasyExcel.read(in,SubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SubjectTree> getAllOneTwoSubject() {

        ArrayList<SubjectTree> list = new ArrayList<>();
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",0);
        List<EduSubject> oneSubject = baseMapper.selectList(queryWrapper);



        QueryWrapper<EduSubject> queryWrapper1 = new QueryWrapper<>();
        queryWrapper.ne("parent_id",0);
        List<EduSubject> twoSubject = baseMapper.selectList(queryWrapper1);



        for (EduSubject eduSubject : oneSubject) {
            SubjectTree subjectTree = new SubjectTree();
            subjectTree.setId(eduSubject.getId());
            subjectTree.setTitle(eduSubject.getTitle());
            list.add(subjectTree);
        }

        for (SubjectTree eduSubject : list) {
            for (EduSubject subject : twoSubject) {
                if(subject.getParentId().equals(eduSubject.getId())){
                    SubjectTree subjectTree = new SubjectTree();
                    subjectTree.setId(subject.getId());
                    subjectTree.setTitle(subject.getTitle());
                    eduSubject.getChildren().add(subjectTree);
                }
            }
        }



        return list;
    }
}
