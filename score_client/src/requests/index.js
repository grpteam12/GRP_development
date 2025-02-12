import axios from 'axios'
import qs from 'qs'
/* eslint-disable */ 
const production = true; // 是否是生产环境

const baseURL = 'http://127.0.0.1:8801'


// 把axios方法赋值给变量ajax
const ajax = axios.create()

// 请求拦截
ajax.interceptors.request.use(config => {
	var headers = {}
	if(config.headers["Content-Type"] != undefined){
		headers["Content-Type"] = config.headers["Content-Type"];
	}
	// headers 是设置即将被发送的自定义请求头。
	config.headers = headers;
	return config
})


// 响应拦截
ajax.interceptors.response.use(resp => {
	switch (resp) {
		case 200:
			return resp.data
			break;
		case 401:
			return {
				data: 'Unauthorized',
				code: 1,
				message: "请先登录"
			}
		case 403:
			return {
				data: 'Forbidden',
				code: 1,
				message: "语法错误"
			}
		case 404:
			return {
				data: 'Not Found',
				code: 1,
				errorMsg: 'Not Found'
			}
		case 504:
			return {
				data: 'GateWayTimeOut',
				code: 1,
				errorMsg: '连接超时'
			}
		case 500:
			return {
				data: 'server Error',
				code: 1,
				errorMsg: '服务器错误'
			}
		default:
			break;
	}
	return resp.data;
})


// log打印
export const log = (name, ...args) => {
  console.log(name, args)
}

// 消息提示
export const showMessage = (page, result) => {
  if(result.code == 1) {
    page.$message.success(result.message, 3)
  } else {
    page.$message.error(result.message, 3)
  }
}

//////////////////////////通用请求接口////////////////////////

/**
 * get请求
 */
export const http_get = (page, url, params, showMsg, callback) => {
  // url += (url.indexOf('?') > -1 ?"&":"?")+ qs.stringify(params)
    ajax.get(url,params).then(res => {
		if(showMsg){
			showMessage(page, res)
		}
		callback(res);
  	});
}



/**
 * post请求
 */
export const http_post = (page, url, params, showMsg, callback) => {
  ajax.post(url, qs.stringify(params), {
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  }).then(res => {
    if(showMsg)
      showMessage(page, res)
  
    callback(res);
  });
}

//获取图片验证码
export const http_img_get = (url, callback, databack) => {
	axios.get(url, {
		responseType: "arraybuffer"
	}).then(res => {
		callback(res.headers["content-type"].replace("=", "").split(";")[1].trim());
		return 'data:image/png;base64,' + btoa(
			new Uint8Array(res.data).reduce((data, byte) => data + String.fromCharCode(byte), '')
		);
	}).then(data => {
		databack(data)
	});
}

// 回车键封装
export const enterKeyDown = (hc) => {
	document.onkeydown = (e) => {
	  var event = e || window.event;
	  if(event.keyCode && event.keyCode == 13){
		hc();
	  } else if(event.which == 13){
		hc();
	  }
	}
  }
  
// 使用同一域名配置
export default {
  baseURL
}
