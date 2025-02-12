<template>
    <div id="userManage">
        <el-col :span="20" class="toolbar">
			<el-form :inline="true" :model="searchForm">
				<!-- <el-form-item>
					<el-input v-model="searchForm.nick_name" placeholder="姓名" clearable></el-input>
				</el-form-item>
                
				<el-form-item>
					<el-button type="primary" size="small" @click="doSearch">查询</el-button>
				</el-form-item> -->
				<el-form-item>
					<el-button type="primary" size="small" @click="add">upload</el-button>
				</el-form-item>
			</el-form>
		</el-col>
        
        <!--列表-->
		<el-table :data="datas" highlight-current-row  stripe border style="width: 100%;font-size: 13px;opacity:0.8"
            :header-cell-style="{background:'#FDF5E6',color:'#606266',fontSize:'13px',textAlign:'center'}" >
			<el-table-column type="index" width="50" align="center">
			</el-table-column>
			<el-table-column prop="name" label="file name" align="center"  sortable>
			</el-table-column>
			
			<el-table-column prop="uploader" label="uploader" align="center"  sortable>
			</el-table-column>
			<el-table-column prop="subTime" label="upload time" align="center"  sortable>
			</el-table-column>
			<el-table-column prop="download" label="download" align="center">
				<template slot-scope="scope">
					<el-link type="success" :href="scope.row.url" target="_blank">down</el-link>
				</template>
			</el-table-column>
			
			<el-table-column label="option" align="center">
				<template slot-scope="scope">
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
                <el-form-item label="uploader" prop="uploader" style="text-align:left">
                   <el-input v-model="publishForm.uploader" placeholder="" disabled></el-input>
                </el-form-item>
                <el-upload
                  style="text-align:left"
                  class="upload-demo"
                  name = 'file'
                  :action="this.$api.file.upload"
                  :limit="1"
                  :on-success="handleSuccess"
                  :file-list="fileList">
                  <el-button size="small" type="primary">upload file</el-button>
                </el-upload>
				
            </el-form>
					
			<div slot="footer" class="dialog-footer">
				<el-button @click="dialog.show = false" size="small">取消</el-button>
				<el-button type="primary" @click="publish" size="small">提交</el-button>
			</div>
		</el-dialog>
    
		 <!-- 弹窗 -->
		 <el-dialog :visible.sync="dialogVisibleA" title="矩阵可视化" width="80%" >
			<div class="charts-container">
				<div id="chart-a" class="chart"></div>
				
				<div id="chart-b" class="chart"></div>
				
				<div id="chart-c" class="chart"></div>
			</div>
		</el-dialog>
	</div>
</template>

<script>
import { log } from '../../requests'
import * as echarts from "echarts";
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
                title:'修改视频',
                width:'50%',
                show:false
            },
            publishForm:{},
            rules:{
              
            },
			currentUser:{},
			fileList:[],
			matrices: {},
          	dialogVisibleA:false
        }
    },
    methods:{
		//上传成功回调
		handleSuccess(response, file, fileList){
			if(response.code == 1){
				this.publishForm.name = response.data.name
				this.publishForm.url = response.data.url
				this.publishForm.dest = response.data.dest
			}
		},
		//加载查询当前用户
		getUser(){
		   this.currentUser = JSON.parse(sessionStorage.getItem('principal'))
		   this.publishForm.uploaderSerial = this.currentUser.serial
		   this.publishForm.uploader = this.currentUser.firstName+this.currentUser.lastName

		},
        //加载查询表格
        loadSearch(){
            this.datas = []
            this.$http.http_post(this,this.$api.file.findAll,this.searchForm,false,(res)=>{
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
			this.dialog.title='upload file',
			this.dialog.show = true
        },
        edit(row){
			this.publishForm = row
			this.dialog.title='edit file',
			this.dialog.show = true
        },
        del(row){
			this.$confirm('此操作将永久删除记录, 是否继续?', '提示', {
			    confirmButtonText: '确定',
			    cancelButtonText: '取消',
			    type: 'warning'
			}).then(() => {
			    this.$http.http_get(this,this.$api.dormitory.remove.replace(/{serial}/,row.serial),null,true,(res)=>{
			        if(res.code == 1){
			            this.loadSearch()
			        }
			    })
			}).catch((e) => {
			});
            
        },
		

        //提交表单
        publish(){
            this.$refs["publishForm"].validate(valid => {
				if (valid) {
					this.$http.http_post(this,this.$api.file.publish,this.publishForm,true,(res)=>{
                        if(res.code == 1){
                            this.loadSearch()
                            this.dialog.show = false

							this.matrices = res.data;
							this.dialogVisibleA = true
							setTimeout(()=>{
								this.initCharts()
							},1000)
                        }
                    })			
				}
			});
        },

		initCharts() {
			console.log("Dialog 已完全打开，开始渲染 ECharts...",this.matrices);
			setTimeout(() => {
			this.renderHeatmap("chart-a", this.matrices.A, "rating_matrix");
			this.renderHeatmap("chart-b", this.matrices.B, "estimated_matrix");
			this.renderHeatmap("chart-c", this.matrices.C, "error_matrix");
			}, 500);
      	},
		  renderHeatmap(elementId, matrix, title) {
      let chartDom = document.getElementById(elementId);
      if (!chartDom) return;

      let chart = echarts.init(chartDom);
      let data = [];

      // 🔹 处理矩阵数据，转换成 ECharts Heatmap 所需格式
      for (let i = 0; i < matrix.length; i++) {
        for (let j = 0; j < matrix[i].length; j++) {
          data.push([j, i, matrix[i][j]]);
        }
      }

      chart.setOption({
        title: { text: title, left: "center" },
        tooltip: { position: "top" },
        grid: { left: "10%", right: "10%", bottom: "10%", containLabel: true },
        xAxis: { type: "category", data: matrix[0].map((_, index) => "X" + index) },
        yAxis: { type: "category", data: matrix.map((_, index) => "Y" + index) },
        visualMap: { min: 0, max: 100, calculable: true, orient: "vertical", left: "right", top: "center" },
        series: [{ name: title, type: "heatmap", data: data, label: { show: true }, emphasis: { itemStyle: { borderColor: "#333", borderWidth: 1 } } }]
      });
    },
    },
    
	created() {
		this.getUser()
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
	.charts-container {
		display: flex;
		flex-wrap: wrap;
		justify-content: space-around;
		gap: 20px;
	}
	.chart {
		width: 100%;
		min-height: 500px; /* 适应更多数据 */
	}
    
</style>