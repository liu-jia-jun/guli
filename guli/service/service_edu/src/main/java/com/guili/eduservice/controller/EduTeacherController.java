package com.guili.eduservice.controller;


import com.guili.eduservice.entity.EduTeacher;
import com.guili.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
 *
 * Swagger
 *
 * @ApiOperation(“对于该方法的注释”) 作用在方法上，对方法进行注释
 *
 * @ApiParam(name = "id",value = "讲师ID",required = true) 作用在参数上，对参数进行约束
 *
 * name 对应参数的名字
 * value 对参数进行解释
 * required 约束该参数是否必须，是否可以为空
 *
 *
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
    @ApiOperation("查询所有教师")
    public List<EduTeacher> test(){
        return eduTeacherService.list(null);
    }

    @DeleteMapping("{id}")
    @ApiOperation("逻辑删除")
    public boolean removeTeacher(@ApiParam(name = "id",value = "讲师ID",required = true) @PathVariable("id") String id){
        return eduTeacherService.removeById(id);
    }

}

