import request from '@/utils/request'

const api_name = '/eduservice/chapter'

export default {

  getNestedTreeList(courseId) {
    return request({
      url: `${api_name}/nested-list/${courseId}`,
      method: 'get'
    })
  },
 
}