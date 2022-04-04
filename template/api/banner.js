import request from "@/utils/request"

export default {
  getList() {
    return request({
      url: `/service_cms/crm_banner/getAllBanner`,
      method: 'get'
    })
  }
}