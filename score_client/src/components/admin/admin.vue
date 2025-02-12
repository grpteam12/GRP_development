<template>
    <div id="admin">
			<el-header>
				<navHeader @publish-event="handlePublish"></navHeader>
			</el-header>
			<el-container style="display: flex;">
				<el-aside width="240px">
					 <el-menu default-active="User Management" class="el-menu-vertical-demo"  background-color="#545c64"
						text-color="#fff" active-text-color="#ffd04b" @select="choice">
						<el-menu-item index="User Management" style="text-align: left;">
							<i class="el-icon-user-solid"></i>
							<span class="title">User Management</span>
						</el-menu-item>
						<el-menu-item index="File Management" style="text-align: left;">
							<i class="el-icon-edit-outline"></i>
							<span class="title">File Management</span>
						</el-menu-item>
						<el-menu-item index="Table Filling" style="text-align: left;">
							<i class="el-icon-edit-outline"></i>
							<span class="title">Table Filling</span>
						</el-menu-item>
					</el-menu>
				</el-aside>

				<el-main style="">
					<user-manage v-if="manageType=='User Management'" ref="bRef"></user-manage>
					<dormitory-manage v-if="manageType=='File Management'"></dormitory-manage>
          <table-manage v-if="manageType=='Table Filling'"></table-manage>
				</el-main>
			</el-container>
       
    </div>
</template>

<script>

import userManage from './userManage.vue'
import navHeader from '../public/navHeader.vue'
import dormitoryManage from '../admin/dormitoryManage.vue'
import tableManage from './tableManage.vue'
export default {
  components: { userManage,navHeader,dormitoryManage,tableManage},
    data(){
        return{
            manageType:1,
            userInfo:{},
            navs:[]
        }
    },

    methods:{
        // selectMenu(index){
        //     this.manageType = index
        // },
        choice(val){
            this.manageType = val
        },
        handlePublish() {
          this.$refs.bRef.find(); // 直接调用 B 组件的 find 方法
        }
    },
    created(){
       document.title="manage"
       this.manageType = 'User Management'
	  
      
    }
}
</script>

<style scoped>
    .title{
        font-size: 18px;
        font-weight: bolder;
        opacity: 0.8;
    }
    
    .exit{
        cursor: pointer;
    }
    .el-header, .el-footer {
        background-color: #B3C0D1;
        color: #333;
        text-align: center;
        line-height: 60px;
    }
  
  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    height: 800px;
  }
  
  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    height: 800px;
  }
  
  /* body > .el-container {
    margin-bottom: 40px;
  } */
  
  /* .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }
  
  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  } */
</style>