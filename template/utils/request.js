import axios from "axios"

const service = axios.create({
  baseURL: "http://localhost:9001",// api的baseURL
  timeout: 20000 // 请求超时时间
})

export default service