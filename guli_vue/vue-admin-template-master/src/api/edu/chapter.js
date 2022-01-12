import request from '@/utils/request'

const api_name = '/eduservice/chapter'

export default {

  getNestedTreeList(courseId) {
    return request({
      url: `${api_name}/nested-list/${courseId}`,
      method: 'get'
    })
  },

  removeById(id) {
    return request({
      url: `${api_name}/deleteChapterInfo/${id}`,
      method: 'delete'
    })
  },

  save(chapter) {
    return request({
      url: `${api_name}/addChapter`,
      method: 'post',
      data: chapter
    })
  },

  getById(id) {
    return request({
      url: `${api_name}/getChapterInfo/${id}`,
      method: 'get'
    })
  },

  updateChapter(chapter) {
    return request({
      url: `${api_name}/updateChapter`,
      method: 'post',
      data: chapter
    })
  }
 
}