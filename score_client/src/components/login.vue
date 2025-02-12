<template>
    <div id="login">
       <div class="left">
           <el-image :src="require('@/assets/bg.webp')"
                style="width:1000px;margin-top:100px;height: 500px;"></el-image>
       </div>
       <div class="right">
           <div class="loginForm">
               <el-tabs type="border-card" >
                    <el-tab-pane label="login" >
                        <el-form :model="loginForm" ref="loginForm">
                            <el-form-item prop='account'>
                                <el-input placeholder="Please enter your account" @input="updateTotal($event)" v-model="loginForm.account" style="width:300px;margin-top:40px">
                                    <template slot="prepend">
                                        <i class="el-icon-user"></i>
                                    </template>
                                </el-input>
                             </el-form-item>
                             <el-form-item prop='password'>
                                 <el-input placeholder="Please enter your password" @input="updateTotal($event)" v-model="loginForm.password" style="width:300px;" type="password">
                                    <template slot="prepend">
                                        <i class="el-icon-info"></i>
                                    </template>
                                </el-input>
                             </el-form-item>
                        </el-form>
                        <el-button class="loginBtn" @click="login">login</el-button>
                    </el-tab-pane>
					<el-tab-pane label="register" >
					    <el-form :model="registerForm" ref="registerForm">
					        <el-form-item prop='account'>
					            <el-input placeholder="" @input="updateTotal($event)" v-model="registerForm.account" style="width:300px;margin-top:40px">
					                <template slot="prepend">
					                   <span>account</span>
					                </template>
					            </el-input>
					         </el-form-item>
					         <el-form-item prop='password'>
					             <el-input placeholder="" @input="updateTotal($event)" v-model="registerForm.password" style="width:300px;" type="password">
					                <template slot="prepend">
					                    <span>password</span>
					                </template>
					            </el-input>
					         </el-form-item>
							 <!-- <el-form-item prop='no'>
							     <el-input placeholder="请输入学号" @input="updateTotal($event)" v-model="registerForm.no" style="width:300px;">
							         <template slot="prepend">
							            <span>学号</span>
							         </template>
							     </el-input>
							  </el-form-item>
							 <el-form-item prop='nickName'>
							     <el-input placeholder="请输入姓名" @input="updateTotal($event)" v-model="registerForm.nickName" style="width:300px;">
							         <template slot="prepend">
							            <span>姓名</span>
							         </template>
							     </el-input>
							  </el-form-item> -->
							 <!-- <el-form-item prop='sex' label="角色" style="margin-left: 100px;">
								 <el-select v-model='registerForm.user_type' style="float: left;">
									 <el-option value="管理员" label="管理员"></el-option>
									 <el-option value="学生" label="学生"></el-option>
								 </el-select>
							 </el-form-item> -->
					    </el-form>
					    <el-button type="primary" style="width: 300px;margin-top: 10px;margin-bottom: 30px;" @click="reg">register</el-button>
					</el-tab-pane>
                </el-tabs>
           </div>
       
	   </div>
    </div>
</template>

<script>
export default {
    data(){
        return{
            registerForm:{},
            loginForm:{},
            rules:{
                account: [{ required: true, message: '请输入账号', trigger: 'change' }],
                password: [{ required: true, message: '请输入密码', trigger: 'change' }],
            }
        }
    },
    methods:{
        updateTotal(e){
			this.$forceUpdate();
		},
        
        //登陆
        login(){
			
            this.$http.http_post(this,this.$api.userAccount.login,this.loginForm,true,(res)=>{
                if(res.code == 1){
                    this.$refs['loginForm'].resetFields()
                    let json = JSON.stringify(res.data)
                    sessionStorage.setItem('principal',json)
					this.$router.push({'path':'/admin'})
					// if (res.data.user_type == '学生'){
					// 	this.$router.push({'path':'/home'})
					// }else{
					// 	this.$router.push({'path':'/admin'})
					// }
                }
            })
        },
        //注册
        reg(){
            this.$http.http_post(this,this.$api.userAccount.register,this.registerForm,true,(res)=>{
                if(res.code == 1){
                   this.$refs['registerForm'].resetFields()
                }
            })
        }
    },

    created(){
        document.title = '登陆'
    }
}
</script>

<style scoped>
    #login{
        display: flex;
        background-color: #F0F8FF;
    }
    .left{
        width: 60%;
        height: 800px;
        margin: 0 auto;
        padding: 10px;
        
    }

    .right{
        width: 35%;
        height: 800px;
        border-left: 2px solid rgb(232, 237, 233);
        margin: 0 auto;
        padding: 10px;
    }
    .loginForm{
        height: 430px;
        margin-top: 160px;
        padding: 10px;
    }

    .loginBtn{
        width: 300px;
        margin-top: 10px;
        margin-bottom: 30px;
        background-image: linear-gradient(to right, #ff9569 0%, #e92758 100%);
        color: white;
    }
    .loginBtn:hover{
        color: white;
    }

    .loginBtn, .loginBtn:focus:not(.buttonDiv:hover){ 
        color: white
       
    }
</style>