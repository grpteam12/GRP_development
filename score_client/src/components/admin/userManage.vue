<template>
    <div id="userManage">
        <el-col :span="20" class="toolbar">
			<el-form :inline="true" :model="searchForm">
				<!-- <el-form-item>
					<el-input v-model="searchForm.nick_name" placeholder="姓名" clearable></el-input>
				</el-form-item> -->
                
				<!-- <el-form-item>
					<el-button type="primary" size="small" @click="doSearch">查询</el-button>
				</el-form-item> -->
				<!-- <el-form-item>
					<el-button type="primary" size="small" @click="add">新增</el-button>
				</el-form-item> -->
			</el-form>
		</el-col>
        
        <!--列表-->
		<el-table :data="datas" highlight-current-row  stripe border style="width: 100%;font-size: 13px;opacity:0.8"
            :header-cell-style="{background:'#FDF5E6',color:'#606266',fontSize:'13px',textAlign:'center'}" >
			<el-table-column type="index" width="50" align="center">
			</el-table-column>
			<el-table-column prop="account" label="User Name" align="center"  sortable>
			</el-table-column>
			<el-table-column prop="password" label="password" align="center"  sortable>
			</el-table-column>
			<el-table-column prop="firstName" label="First Name" align="center"  sortable>
			</el-table-column>
			<el-table-column prop="lastName" label="Last Name" align="center"  sortable>
			</el-table-column>
            <el-table-column prop="studentId" label="Stdudent Id" align="center"  sortable>
			</el-table-column>
            <el-table-column prop="address" label="address" align="center"  sortable>
			</el-table-column>
            <el-table-column prop="city" label="city" align="center"  sortable>
			</el-table-column>
            <el-table-column prop="country" label="country" align="center"  sortable>
			</el-table-column>
            <el-table-column prop="postCode" label="postCode" align="center"  sortable>
			</el-table-column>
			<el-table-column prop="subTime" label="注册时间" align="center"  sortable>
			</el-table-column>
			<el-table-column label="option" align="center">
				<template slot-scope="scope">
					<!-- <el-button type="success" size="small" @click="edit(scope.row)">edit</el-button> -->
					<el-button type="danger" size="small" @click="del(scope.row)">delete</el-button>
				</template>
			</el-table-column>
		</el-table>

        <!--工具条-->
		<div class="page">
			<el-pagination
				v-if="datas.length > 0"
					background
					layout="prev, pager, next"
					@current-change="pageChange"
					:current-page="searchForm.page"
					:page-size="searchForm.pageSize"
					:total="searchForm.total"
					class="fr"
			></el-pagination>
		</div>

        <el-dialog :title="dialog.title" :close-on-click-modal="false" :visible.sync="dialog.show" :width="dialog.width">
			<el-form :model="publishForm"  ref="publishForm" :rules="rules" label-position="left" label-width="90px">
               <el-form-item label="账号" prop="account" style="text-align:left">
                   <el-input v-model="publishForm.account" placeholder=""></el-input>
               </el-form-item>
               <el-form-item label="密码" prop="password" style="text-align:left">
                   <el-input v-model="publishForm.password" placeholder=""></el-input>
               </el-form-item>
               <el-form-item label="姓名" prop="nickName" style="text-align:left">
                   <el-input v-model="publishForm.nickName" placeholder=""></el-input>
               </el-form-item>
			   <el-form-item label="学号" prop="no" style="text-align:left">
			       <el-input v-model="publishForm.no" placeholder=""></el-input>
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
import { replace } from 'core-js/library/es6/symbol'

export default {
    data(){
        return{
            searchForm:{
                page:1,
                pageSize:5,
                total:0,
            },
            datas:[],
            dialog:{
                title:'修改用户',
                width:'50%',
                show:false
            },
            publishForm:{},
            rules:{
                account:[{required: true,message: "账号不能为空",trigger: "blur"}],
                name:[{required: true,message: "姓名不能为空",trigger: "blur"}],
                role:[{required: true,message: "角色不能为空",trigger: "blur"}],
            },
			
        }
    },
    methods:{
		find() {
           this.loadSearch()
        },
        //加载查询表格
        loadSearch(){
            this.datas = []
            this.$http.http_post(this,this.$api.userAccount.findAll,this.searchForm,false,(res)=>{
                if(res.code == 1){
                    this.datas = res.data
                    this.searchForm.total = res.count
                }
            })
        },

        doSearch(){
            this.searchForm.page = 1
            this.loadSearch()
        },
        //分页
        pageChange(page){
            this.searchForm.page = page
            this.loadSearch()
        },
        add(){
			this.dialog.title='新增用户',
			this.dialog.show = true
        },
        edit(row){
			this.publishForm = row
			this.dialog.title='修改用户',
			this.dialog.show = true
        },
        del(row){
			this.$confirm('此操作将永久删除记录, 是否继续?', '提示', {
			    confirmButtonText: '确定',
			    cancelButtonText: '取消',
			    type: 'warning'
			}).then(() => {
				this.$http.http_get(this,this.$api.userAccount.remove.replace(/{serial}/,row.serial),null,true,(res)=>{
				    if(res.code == 1){
				        this.loadSearch()
				    }
				})
				
			    
			}).catch((e) => {
				console.log('一次',e)
			});
            
        },

        //提交表单
        publish(){
            this.$refs["publishForm"].validate(valid => {
				if (valid) {
					this.$http.http_post(this,this.$api.userAccount.publish,this.publishForm,true,(res)=>{
                        if(res.code == 1){
                            this.loadSearch()
                            this.dialog.show = false
                        }
                    })			
				}
			});
        }

    },
	
	created() {
		this.loadSearch()
	}
    
}
</script>

<style scoped>
    .toolbar{
       width: 100%;
       text-align: left;
    }
    .page{
        float: right;
        margin-top: 15px;
    }
    
</style>