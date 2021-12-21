package com.guili.eduservice.controller;


import com.guili.eduservice.entity.EduTeacher;
import com.guili.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author 刘佳俊
 * @since 2021-12-16
 */
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {

    @Autowired
    EduTeacherService eduTeacherService;

    /**
     * 查询教师列表
     * @return
     */
    @GetMapping("/findAll")
    public List<EduTeacher> test(){
        return eduTeacherService.list(null);
    }

    @DeleteMapping("{id}")
    public boolean removeTeacher(@PathVariable("id") String id){
        return eduTeacherService.removeById(id);
    }

}

