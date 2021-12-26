import request from "@/utils/request"

export default {

    getTeacherList(current, limit, teacherQuery) {
        return request({
            url: `pageTeacherCondition/${current}/${limit}`,
            method: 'post',
            data: teacherQuery

        })
    }




}