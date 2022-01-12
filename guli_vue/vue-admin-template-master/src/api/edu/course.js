import request from "@/utils/request"

export default {


    updateCourse(courseInfoForm){
        return request({
            url: `/eduservice/course/updateCourseInfo`,
            method: "post",
            data: courseInfoForm
        })
    },


    saveCourse(courseInfoForm) {
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
    },
    getCourseInfo(courseId) {
        return request({
            url: `/eduservice/course/getCourseInfo/${courseId}`,
            method: "get",

        })
    },


}