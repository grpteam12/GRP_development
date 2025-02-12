import http from '../index'

const fileBaseUrl = http.baseURL 

export default {
  upload: fileBaseUrl + '/v1/api/commen/upload',
  publish:fileBaseUrl + "/v1/api/fmg/publish",
  remove:fileBaseUrl + "/v1/api/fmg/remove/{serial}",
  findAll:fileBaseUrl + "/v1/api/fmg/find/all"
}