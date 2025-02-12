import http from '../index'

const fileBaseUrl = http.baseURL 

export default {
  publish:fileBaseUrl + "/v1/api/table/publish",
  remove:fileBaseUrl + "/v1/api/table/remove/{serial}",
  findAll:fileBaseUrl + "/v1/api/table/find/all"
}