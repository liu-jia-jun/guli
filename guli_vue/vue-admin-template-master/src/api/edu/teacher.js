import request from "@/utils/request"

export default {

    getTeacherListPage(current, limit, teacherQuery) {
        return request({
            url: `/eduservice/edu-teacher/pageTeacherCondition/${current}/${limit}`,
            method: 'post',
            data: teacherQuery

        })
    },
    deleteTeacher(id) {
        return request({
            url: `/eduservice/edu-teacher/deleteTeacher/${id}`,
            method: "delete",
        })
    },
    addTeacher(teacher) {
        return request({
            url: `/eduservice/edu-teacher/addTeacher`,
            method: "post",
            data: teacher
        })
    },
    getInfo(id) {
        return request({
            url: `/eduservice/edu-teacher/getTeacher/${id}`,
            method: "get",

        })
    },

    updateTeacherInfo(teacher) {
        return request({
            url: `/eduservice/edu-teacher/updateTeacher`,
            method: "post",
            data: teacher

        })
    },


}