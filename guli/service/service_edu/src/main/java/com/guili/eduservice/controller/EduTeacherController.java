package com.guili.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.commonutils.Result;
import com.guili.eduservice.entity.EduTeacher;
import com.guili.eduservice.entity.vo.TeacherQuery;
import com.guili.eduservice.service.EduTeacherService;
import com.service_base.exceptionhandler.GuliException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
@CrossOrigin// 用于解决跨域问题
public class EduTeacherController {

    @Autowired
    EduTeacherService eduTeacherService;

    /**
     * 查询教师列表
     * @return
     */
    @GetMapping("/findAll")
    @ApiOperation("查询所有教师")
    public Result findAll(){
        return Result.ok().data("items",eduTeacherService.list(null));
    }

    @DeleteMapping("{id}")
    @ApiOperation("逻辑删除")
    public Result removeTeacher(@ApiParam(name = "id",value = "讲师ID",required = true) @PathVariable("id") String id){

        if(eduTeacherService.removeById(id)){
            return Result.ok();
        }else {
            return Result.error();
        }

    }

    /**
     * 通过获取的页面数和页面中需要显示的数据条数进行分页查询
     * @param current
     * @param limit
     * @return
     */
    @GetMapping("pageTeacher/{current}/{limit}")
    public Result pageListTeacher(@PathVariable("current") long current,
                                  @PathVariable("limit") long limit){
        // 创建page对象
        Page<EduTeacher> pageTeacher = new Page<>();

        // 调用方法实现分页
        // 调用方法时，底层封装，把分页所有数据封装到pageTeacher对象中

        eduTeacherService.page(pageTeacher,null);

        long total = pageTeacher.getTotal();// 得到总记录数

        List<EduTeacher> teacherList = pageTeacher.getRecords();// 得到分页中数据的list集合


        return Result.ok().data("total",total).data("rows",teacherList);

    }

    // 条件分页查询
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    @CrossOrigin
    public Result pageTeacherCondition(@PathVariable("current") long current,
                                       @PathVariable("limit") long limit,
                                       @RequestBody TeacherQuery teacherQuery){
        // 创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);

        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        if(StringUtils.isEmpty(name)) {
            //构建条件
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)) {
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create",end);
        }


        //调用方法实现条件查询分页
        eduTeacherService.page(pageTeacher,wrapper);
        long total = pageTeacher.getTotal();// 得到总记录数

        List<EduTeacher>  teacherList = pageTeacher.getRecords();// 得到分页中数据的list集合


        // 链式编程，需要在方法中返回this
        return Result.ok().data("total",total).data("rows",teacherList);
    }


    @PostMapping("addTeacher")
    public Result addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if(save){
            return Result.ok();
        }else{
            return Result.error();
        }
    }

    // 根据讲师ID进行查询
    @GetMapping("getTeacher/{id}")
    public Result getTeacher(@PathVariable("id") String id){
        EduTeacher teacher = eduTeacherService.getById(id);
        return Result.ok().data("teacher",teacher);
    }


    // 讲师修改功能
    @PostMapping("updateTeacher")
    public Result updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean b = eduTeacherService.updateById(eduTeacher);
        if(b){

            return Result.ok();
        }else{
            return Result.error();
        }
    }

    // 测试异常处理
    @GetMapping("test")
    public Result testException(){
        try{
            int i = 10/0;
        }catch (Exception e){
            // 手动抛出自定义异常，执行该自定义异常
          throw new GuliException(20002,"宝，这里出错了");
        }
        return Result.ok();
    }





}

