<!-- eslint-disable -->
<template>
	<!--用户端导航头部-->
    <div id="header">
      <div class="site-nav-bd">
        <ul class="site-nav-bd-r" style="display:flex">
          <li class="site-nav-menu">
            <div class="mine" style="margin-top:2px">
              <a  target="_top" class="site-nav-login-info-nick" @click="myInfo">我的信息</a>
            </div>
          </li>
          
		  <li class="site-nav-menu">
		    <div class="mine" style="margin-top:2px">
		      <span class="site-nav-login-info-nick" @click="goOut">退出</span>
		    </div>
		  </li>
		  
        </ul>
      </div>
	  
	 
	  
	  <!--我的信息-->
	  <el-drawer
		:size='500'
		title="我的信息"
		:visible.sync="drawerInfo"
		:direction="direction"
		>
	  	<!--列表-->
	  	<div style="padding: 20px;">
	  		<el-form :model="publishForm"   ref="publishForm"   label-position="left" label-width="90px">
	  		    <el-form-item label="账号" prop="account" style="text-align:left">
	  		        <el-input v-model="publishForm.account" disabled></el-input>
	  		    </el-form-item>
				<el-form-item label="密码" prop="password" style="text-align:left">
				    <el-input v-model="publishForm.password" ></el-input>
				</el-form-item>
	  		    <el-form-item label="昵称" prop="real_name" style="text-align:left">
	  		        <el-input v-model="publishForm.real_name"></el-input>
	  		    </el-form-item>
	  		    <el-form-item label="手机号" prop="phone" style="text-align:left">
	  		        <el-input v-model="publishForm.phone"></el-input>
	  		    </el-form-item>
	  			<el-form-item label="学号" prop="no" style="text-align:left">
	  			    <el-input v-model="publishForm.no" disabled></el-input>
	  			</el-form-item>
	  		</el-form>
			<el-button type="primary" style="float: left;" @click="update">提交</el-button>
	  	</div>
	  		
	  </el-drawer>
    </div>
</template>

<script>
/* eslint-disable */
export default {
    data() {
      return {
		  drawer:false,
		  drawerInfo:false,
		  user:{},
		  direction: 'rtl',
		  deliverys:[],
		  searchForm:{
			  'page':1,
			  'pageSize':100
		  },
		  publishForm:{},
		  datas:[]
      };
    },

    methods: {
		update(){
			this.$http.http_post(this,this.$api.userAccount.publish,this.publishForm,true,(res)=>{
			    if(res.code == 1){
			       let t = JSON.stringify(this.publishForm)
				   sessionStorage.setItem('principal',t)
			    }
			})
		},
		//我的信息
		myInfo(){
			this.publishForm = this.user
			this.drawerInfo = true
		},
		
			
		//退出
        goOut(){
          sessionStorage.clear()
          this.$router.push({path:'/login'})
        },
		
		
		
    },
    created(){
       this.user = JSON.parse(sessionStorage.getItem('principal'))
    }

  }
</script>


<style scoped>
  /* eslint-disable */
    #header{
      z-index: 10000;
      width: 100%;
      background-color: #f5f5f5;
      border-bottom: 1px solid #eee;
    }
    .site-nav-bd {
      margin: 0 auto;
      width: 100%;
      height: 54px;
      background: #f5f5f5;
	  float: right;
    }
    .site-nav-bd-l{
      float: left;
      margin: 10px;
    }
    .site-nav-login-info-nick {
        float: left;
        margin-right: 7px;
        font-size: 14px;
        color: #6C6C6C;
    }
    .site-nav-menu{
      margin-right: 20px;
    }
    .site-nav-bd-r{
      float: right;
      margin: 15px;
    }
    .el-dropdown-link {
      cursor: pointer;
      color: #6C6C6C;
      font-size: 14px;
    }
    
    
</style>