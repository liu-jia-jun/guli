package com.guli.service_edu.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 刘佳俊
 */
@ApiModel(value = "课时信息")
@Data
public class VideoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private Boolean free;
    private String videoOriginalName;
    private String videoSourceId;// 阿里视频资源id
}
