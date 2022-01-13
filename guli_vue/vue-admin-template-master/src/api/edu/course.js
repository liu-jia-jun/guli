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
    getCoursePublishInfo(courseId) {
        return request({
            url: `/eduservice/course/course-publish-info/${courseId}`,
            method: "get",

        })
    },
    publishCourse(courseId){
        return request({
            url: `/eduservice/course/publishCourse/${courseId}`,
            method: "post",

        })
    },
    getAllCourse(){
        return request({
            url:"/eudservice/course/getAllCourse",
            method:"get"
        })
    },
    getPageListCourse(current,limit){
        return request({
            url:`/eduservice/course/getPageListCourse/${current}/${limit}`,
            method:"get"
        })
    },

    searchCourse(current,limit,searchObj){
        return request({
            url:`/eudservice/course/querySelectCourse/${current}/${limit}`,
            method:"post",
            data:searchObj
        })
        
    },
    deleteCourseByID(id){
        return request({
            url:`/eudservice/course/deleteCourse/${current}/${limit}`,
            method:"delete",
             
        })
    }

}