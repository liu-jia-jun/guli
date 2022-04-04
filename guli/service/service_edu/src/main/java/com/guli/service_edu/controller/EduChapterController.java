package com.guli.service_edu.controller;


import com.commonutils.Result;

import com.guli.service_edu.entity.EduChapter;
import com.guli.service_edu.entity.vo.ChapterVo;
import com.guli.service_edu.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author 刘佳俊
 * @since 2022-01-11
 */
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
@Api(description="课程章节管理")
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;


    @ApiOperation(value = "嵌套章节数据列表")
    @GetMapping("nested-list/{courseId}")
    public Result nestedListByCourseId(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId){

        List<ChapterVo> chapterVoList = eduChapterService.nestedList(courseId);
        System.out.println("ccccccccccccccc"+chapterVoList);
        return Result.ok().data("items", chapterVoList);
    }


    // 添加章节
    @PostMapping("/addChapter")
    public Result addChapter(@RequestBody EduChapter eduChapter){
        boolean save = eduChapterService.save(eduChapter);
        return save?Result.ok().data("msg","保存成功"):Result.error().data("msg","保存失败");
    }

    // 更新章节
    @PostMapping("/updateChapter")
    public Result updateChapter(@RequestBody EduChapter eduChapter){
        boolean b = eduChapterService.updateById(eduChapter);
        return b?Result.ok().data("msg","更新成功"):Result.error().data("msg","更新失败");
    }

    @GetMapping("/getChapterInfo/{chapterId}")
    public Result getChapterInfo(@PathVariable("chapterId") String id){
        EduChapter chapter = eduChapterService.getById(id);
        return Result.ok().data("chapter",chapter);
    }

    @DeleteMapping("/deleteChapterInfo/{chapterId}")
    public Result deleteChapter(@PathVariable("chapterId") String id){


        boolean b = eduChapterService.deleteById(id);

        return b?Result.ok():Result.error();

    }


}

