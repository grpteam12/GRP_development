<template>
    <div id="userManage">
		<el-col :span="20" class="toolbar">
			<el-form :inline="true" :model="searchForm">
				<el-form-item>
					<el-button type="primary" size="small" @click="add">table config</el-button>
				</el-form-item>
			</el-form>
		</el-col>
    
    <!--列表-->
		<el-table :data="datas" highlight-current-row  stripe border style="width: 100%;font-size: 13px;opacity:0.8"
            :header-cell-style="{background:'#FDF5E6',color:'#606266',fontSize:'13px',textAlign:'center'}" >
			<el-table-column type="index" width="50" align="center">
			</el-table-column>
			<el-table-column prop="userName" label="User Name" align="center"  sortable>
			</el-table-column>
	
			<el-table-column prop="subTime" label="Sub Time" align="center"  sortable>
			</el-table-column>
			<el-table-column label="option" align="center">
				<template slot-scope="scope">
					<!-- <el-button type="success" size="small" @click="edit(scope.row)">edit</el-button> -->
					<el-button type="danger" size="small" @click="del(scope.row)">delete</el-button>
          <el-button type="success" size="small" @click="edit(scope.row)">detail</el-button>
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

		<!--表格配置-->
		<el-dialog :title="dialog.title" :close-on-click-modal="false" :visible.sync="dialog.show" :width="dialog.width">
			<el-form :model="configForm"  ref="configForm"  label-position="left" label-width="90px">
                <el-form-item label="rows" prop="rows" style="text-align:left">
					<el-input-number v-model="configForm.rows"  :min="1" :max="99" label="rows"></el-input-number>
                </el-form-item>
              
				<el-form-item label="cols" prop="cols" style="text-align:left">
					<el-input-number v-model="configForm.cols"  :min="1" :max="10" label="cols"></el-input-number>
                </el-form-item>
            </el-form>
					
			<div slot="footer" class="dialog-footer">
				<el-button @click="dialog.show = false" size="small">cancel</el-button>
				<el-button type="primary" @click="publishConfig" size="small">config</el-button>
			</div>
		</el-dialog>

    <!--填写内容-->
		<el-dialog :visible.sync="dialogVisible" title="table" width="50%">
			<div class="table-container">
				<table v-if="tableData.length>0">
					<tr v-for="(row, rowIndex) in rows" :key="rowIndex">
						<td v-for="(col, colIndex) in cols" :key="colIndex" >
							<input  v-model="tableData[rowIndex][colIndex]" />
						</td>
					</tr>
				</table>
				<el-button style="margin-top: 20px;" type="primary" @click="submitTable">submit</el-button>
			</div>
		</el-dialog>


    <el-dialog :visible.sync="dialogVisibleDetail" title="detail" width="50%">
			<div class="table-container">
				<table v-if="tableDetail.length>0">
					<tr v-for="(row, rowIndex) in tableDetail" :key="rowIndex">
            <td v-for="(col, colIndex) in Object.values(row)" :key="colIndex">
              <input v-model="tableDetail[rowIndex][colIndex]" disabled/>
            </td>
					</tr>
				</table>
			</div>
		</el-dialog>

     <!-- 弹窗 -->
     <el-dialog :visible.sync="dialogVisibleA" title="矩阵可视化" width="50%" >
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
           
            publishForm:{},
            rules:{
              
            },
			    currentUser:{},
			    fileList:[],
			      dialog:{
                title:'table config',
                width:'30%',
                show:false
            },
          configForm:{},
          dialogVisible: false,
          rows:4,
          cols:4,
          tableData: [],
          dialogVisibleDetail:false,
          tableDetail:[],
          matrices: {},
          dialogVisibleA:false
        }
    },
    methods:{
		  currentUserInfo(){
          this.currentUser = JSON.parse(sessionStorage.getItem('principal'))
      },
      add(){
        this.dialog.show = true
      },
      publishConfig(){
        this.rows = this.configForm.rows;
        this.cols = this.configForm.cols;
        this.tableData = Array.from({ length: this.rows }, () => Array(this.cols).fill(''));
        this.tableData[0][0] = 'Assessor'
        this.dialogVisible = true;
      },
      submitTable(){
        this.matrices = []
        let form = {
          'userSerial':this.currentUser.serial,
          'userName':this.currentUser.firstName+this.currentUser.lastName,
          'datas':JSON.stringify(this.tableData)
        }
        this.$http.http_post(this,this.$api.table.publish,form,false,(res)=>{
            if(res.code == 1){
                this.loadSearch()
                this.dialog.show = false
                this.dialogVisible = false
                this.tableData = []

                this.matrices = res.data;
                this.dialogVisibleA = true
                setTimeout(()=>{
                  this.initCharts()
                },1000)

            }
        })
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


      loadSearch(){
        //加载查询视频
        this.datas = []
        this.$http.http_post(this,this.$api.table.findAll,this.searchForm,false,(res)=>{
          if(res.code == 1){
              this.datas = res.data
          }
        })
      },
      //分页
      pageChange(page){
            this.searchForm.page = page
            this.loadSearch()
        },
        
        edit(row){
          let arr = JSON.parse(row.datas)

          // 深拷贝并清除 Vue 响应式追踪
          let tableDetail = arr.map(row => row.slice());
          // 将 tableDetail 更新到 Vue 实例的数据中
          this.tableDetail = tableDetail;
          this.dialogVisibleDetail = true;
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
		 
    },
    
	created() {
		this.currentUserInfo()
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
	.table-container {
  overflow-x: auto;
  max-width: 100%;
}

table {
  width: 100%;
  border-collapse: collapse;
  table-layout: fixed;
}

td {
  padding: 8px;
  text-align: center;
  border-left: 1px solid #888;
  border-right: 1px solid #888;
  border-bottom: 1px solid #888;
  border-top: 1px solid #888;
}

td:first-child {
  /* border-left: none; */
}

td:last-child {
  /* border-right: none; */
}

input {
  width: 100%;
  height: 30px;
  box-sizing: border-box;
  text-align: center;
  border: none;
  padding: 5px;
  font-size: 14px;
}
.charts-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.chart {
  width: 100%;
  height: 300px;
  margin-bottom: 20px;
}


</style>