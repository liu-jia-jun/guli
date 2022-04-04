package com.guli.service_edu.controller;


import com.commonutils.Result;
import com.guli.service_edu.entity.vo.SubjectTree;
import com.guli.service_edu.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author 刘佳俊
 * @since 2022-01-10
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin// 用于解决跨域问题
public class EduSubjectController {


    @Autowired
    EduSubjectService eduSubjectService;

    @PostMapping("/addSubject")
    public Result addSubject(MultipartFile filed){
        eduSubjectService.saveSubject(filed);
        return Result.ok();
    }

    // 课程分类列表（树形）
    @GetMapping("/getAllSubject")
    public Result getAllSubject(){
        List<SubjectTree> list = eduSubjectService.getAllOneTwoSubject();
        return Result.ok().data("list",list);
    }


}

