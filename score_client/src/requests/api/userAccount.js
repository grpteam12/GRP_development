import http from '../index'

const baseURL = http.baseURL

export default{
    register: baseURL + "/v1/api/user/reg",
	login:baseURL + "/v1/api/user/login",
	publish:baseURL + "/v1/api/user/publish",
	findAll:baseURL + '/v1/api/user/find/condition',
    remove: baseURL + "/v1/api/user/remove/{serial}",
    
}