package com.guili.eduservice.controller;


import com.commonutils.Result;
import com.guili.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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


}

