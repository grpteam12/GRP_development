<template>
    <div id="navHeader">
        <div class='title' type="danger">Score Management</div>
            <div class="userInfo">
                <div style="margin-left:-150px;font-size: 12px;">
                    Hello，{{cur.firstName}}
                </div>
            <div @click="editUser" style="margin-left:10px;margin-top:10px;margin-right:20px">
                <el-avatar  style="width:30px;height:30px" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
            </div>
            <div class="exit" @click="exit" style="font-size: 12px;">退出</div>
        </div>

        <el-dialog :title="dialog.title" :close-on-click-modal="false" :visible.sync="dialog.show" :width="dialog.width">
			<el-form :model="publishForm"  ref="publishForm"  label-position="left" label-width="90px">
               <el-form-item label="User Name" prop="account" style="text-align:left">
                   <el-input v-model="publishForm.account" placeholder="" disabled></el-input>
               </el-form-item>
               <el-form-item label="password" prop="password" style="text-align:left">
                   <el-input v-model="publishForm.password" placeholder=""></el-input>
               </el-form-item>
               <el-form-item label="First Name" prop="firstName" style="text-align:left">
                   <el-input v-model="publishForm.firstName" placeholder=""></el-input>
               </el-form-item>
			   <el-form-item label="Last Name" prop="lastName" style="text-align:left">
			       <el-input v-model="publishForm.lastName" placeholder=""></el-input>
			   </el-form-item>
               <el-form-item label="Student Id" prop="lastName" style="text-align:left">
			       <el-input v-model="publishForm.studentId" placeholder=""></el-input>
			   </el-form-item>
               <el-form-item label="email" prop="email" style="text-align:left">
                   <el-input v-model="publishForm.email" placeholder=""></el-input>
               </el-form-item>
               <el-form-item label="address" prop="address" style="text-align:left">
                   <el-input v-model="publishForm.address" placeholder=""></el-input>
               </el-form-item>
               <el-form-item label="city" prop="city" style="text-align:left">
                   <el-input v-model="publishForm.city" placeholder=""></el-input>
               </el-form-item>
               <el-form-item label="country" prop="country" style="text-align:left">
                   <el-input v-model="publishForm.country" placeholder=""></el-input>
               </el-form-item>
               <el-form-item label="Post code" prop="postCode" style="text-align:left">
                   <el-input v-model="publishForm.postCode" placeholder=""></el-input>
               </el-form-item>
            </el-form>
					
			<div slot="footer" class="dialog-footer">
				<el-button @click="dialog.show = false" size="small">取消</el-button>
				<el-button type="primary" @click="publish" size="small">提交</el-button>
			</div>
		</el-dialog>
    </div>
</template>

<script>
export default {
    data(){
        return{
            cur:{},
            dialog:{
                title:'Edit Profile',
                width:'50%',
                show:false
            },
            publishForm:{}
        }
    },
    methods:{
        //退出
        exit(){
             this.$confirm('是否退出?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
                }).then(() => {
                    sessionStorage.clear()
                    this.$router.push({path:'/login'})
                }).catch(() => {
                          
            });
        },
        currentUser(){
            this.cur = JSON.parse(sessionStorage.getItem('principal'))
            console.log(this.cur)
        },
        editUser(){
          this.publishForm= this.cur
          this.dialog.show = true
        },
        publish(){
            //console.log('保存',this.publishForm)
            this.$http.http_post(this,this.$api.userAccount.publish,this.publishForm,true,(res)=>{
                if(res.code == 1){
                    this.$emit('publish-event'); // 通知父组件
                    //更新缓存
                    let json = JSON.stringify(res.data)
                    sessionStorage.setItem('principal',json)
                    this.dialog.show = false
                }
            })
        }
    },
    created(){
        this.currentUser()
    }
}
</script>

<style scoped>
   
    .title{
        float: left;
        font-size: 15px;
        font-weight: bolder;
        margin-left: 50px;
        opacity: 0.8;
    }
    .userInfo{
        float: right;
        display:inline-flex
    }

    .exit{
        cursor: pointer;
    }
</style>