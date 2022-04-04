package com.guli.service_edu.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘佳俊
 */
@Data
public class SubjectTree {


    private String id;
    private String title;
    private List<SubjectTree> children = new ArrayList<>();

}
