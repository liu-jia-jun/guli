package com.guli.service_edu.service;

import com.guli.service_edu.entity.EduCourse;
import com.guli.service_edu.entity.EduTeacher;

import java.util.List;

public interface IndexFrontService {


    List<EduCourse> getCourseList();

    List<EduTeacher> getTeacherList();
}
