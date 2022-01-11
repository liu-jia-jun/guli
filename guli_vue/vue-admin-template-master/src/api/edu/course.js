import request from "@/utils/request"

export default {


    saveOrUpdate(courseInfoForm) {
        return request({
            url: `/eduservice/course/addCourseInfo`,
            method: "post",
            data: courseInfoForm
        })
    },
    getTeacherList() {
        return request({
            url: `/eduservice/edu-teacher/findAll`,
            method: "get",

        })
    },
    getSubjectList() {
        return request({
            url: `/eduservice/subject/getAllSubject`,
            method: "get",

        })
    }


}