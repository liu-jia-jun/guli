package com.guili.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 刘佳俊
 */
@Data
public class TeacherQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "教师名称，模糊查询")
    private String name;

    @ApiModelProperty(value = "头衔 1高级讲师，2首席讲师")
    private Integer level;

    @ApiModelProperty(value = "查询开始时间",example = "2021-12-22 00:01:00")
    private String begin;// 注意这里使用的是String 类型，前端传过来的数据无需进行类型转换

    @ApiModelProperty(value = "查询结束时间",example = "2021-12-22 00:01:00")
    private String end;// 注意这里使用的是String 类型，前端传过来的数据无需进行类型转换


}
