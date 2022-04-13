package com.guli.service_edu.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.commonutils.Result;
import com.commonutils.bean.CourseWebVoOrder;
import com.guli.service_edu.entity.EduCourse;
import com.guli.service_edu.entity.vo.*;
import com.guli.service_edu.service.EduChapterService;
import com.guli.service_edu.service.EduCourseService;
import com.guli.service_edu.service.EduSubjectService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 刘佳俊
 */
@RestController
@RequestMapping("/eduservice/coursefront")
@CrossOrigin
public class CourseController {
    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduSubjectService subjectService;
    @ApiOperation(value = "分页课程列表")
    @PostMapping(value = "{page}/{limit}")
    public Result pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
            @RequestBody(required = false) CourseQueryVo courseQuery){
        Page<EduCourse> pageParam = new Page<EduCourse>(page, limit);
        Map<String, Object> map = courseService.pageListWeb(pageParam, courseQuery);
        return  Result.ok().data(map);
    }

    @GetMapping("/list2")
    public Result list2(){
        List<SubjectTree> items = subjectService.getAllOneTwoSubject();
        return Result.ok().data("items",items);
    }

    @Autowired
    private EduChapterService chapterService;

    @ApiOperation(value = "根据ID查询课程")
    @GetMapping(value = "{courseId}")
    public Result getById(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId){

        //查询课程信息和讲师信息
        CourseWebVo courseWebVo = courseService.selectInfoWebById(courseId);

        //查询当前课程的章节信息
        List<ChapterVo> chapterVoList = chapterService.nestedList(courseId);

        return Result.ok().data("course", courseWebVo).data("chapterVoList", chapterVoList);
    }


    @GetMapping("getDto/{courseId}")
    public CourseWebVoOrder getCourseInfoDto(@PathVariable String courseId) {
        CourseInfoForm courseInfoForm = courseService.getCourseInfo(courseId);
        CourseWebVoOrder courseInfo = new  CourseWebVoOrder();
        BeanUtils.copyProperties(courseInfoForm,courseInfo);
        return courseInfo;
    }

}
