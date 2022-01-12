import request from '@/utils/request'

const api_name = '/eduservice/video'

export default {

  addVideo(video){
    return request({
      url: `${api_name}/addVideo`,
      method: 'post',
      data: video
    })
  },
  deleteVideo(id){
    return request({
      url: `${api_name}/deleteVideo/${id}`,
      method: 'get',
      
    })
  },
  updateVideo(video){
    return request({
      url: `${api_name}/updateVideo`,
      method: 'post',
      data: video
    })
  },
  getVideo(id){
    return request({
      url: `${api_name}/getVideo/${id}`,
      method: 'get',
      
    })
  },
}