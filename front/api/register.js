import request from '@/utils/request'

export default {
  //根据手机(邮箱)号码发送短信
  getMobile(mobile) {
    return request({
      url: `/edumsm/send/${mobile}`,
      method: 'get'
    })
  },
  //用户注册
  submitRegister(formItem) {
    return request({
      url: `/ucenterservice/apimember/register`,
      method: 'post',
      data: formItem
    })
  }
}