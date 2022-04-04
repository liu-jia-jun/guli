package com.guli.service_edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.service_edu.entity.EduSubject;
import com.guli.service_edu.entity.excel.SubjectData;
import com.guli.service_edu.service.EduSubjectService;
import com.service_base.exceptionhandler.GuliException;

/**
 * @author 刘佳俊
 */

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    private EduSubjectService eduSubjectService;

    public SubjectExcelListener(){}
    public SubjectExcelListener(EduSubjectService eduSubjectService){
        this.eduSubjectService = eduSubjectService;
    }


    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            throw new GuliException(20001, "文件数据为空");
        }


        // 一行一行的进行读取，每次读取两个值，第一个值一级分类，第二个二级分裂
        // 判断以及分类是否重复

        // 一级分类
        String oneName=subjectData.getOneSubjectName();
        EduSubject one = this.existOneSubject(oneName);
        if(one==null){
            EduSubject eduSubject = new EduSubject();
            eduSubject.setParentId("0");
            eduSubject.setTitle(oneName);
            eduSubjectService.save(eduSubject);
        }

        // 二级分类
        String pid = this.existOneSubject(oneName).getId();
        String twoName = subjectData.getTwoSubjectName();
        EduSubject two = this.existTwoSubject(twoName,pid);
        if(two==null){
            EduSubject eduSubject = new EduSubject();
            eduSubject.setParentId(pid);
            eduSubject.setTitle(twoName);
            eduSubjectService.save(eduSubject);
        }

    }

    // 判断一级分类是否重复
    private EduSubject existOneSubject(String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject oneSubject = eduSubjectService.getOne(wrapper);
        return oneSubject;
    }

    // 判断二级分类是否重复
    private EduSubject existTwoSubject(String name,String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject oneSubject = eduSubjectService.getOne(wrapper);
        return oneSubject;
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
