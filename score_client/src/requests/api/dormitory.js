import http from '../index'

const baseURL = http.baseURL

export default{
	publish:baseURL + "/v1/api/video/publish",
	findAll:baseURL + '/v1/api/video/find/condition',
    remove: baseURL + "/v1/api/video/remove/{serial}",
    
	

}