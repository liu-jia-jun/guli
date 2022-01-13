<template>
  <div class="app-container">
    <!-- 查询条件输入 -->
    <el-form :inline="true" :model="teacherQuery" class="demo-form-inline">
      <el-form-item label="讲师姓名">
        <el-input size="mini" v-model="teacherQuery.name" placeholder="讲师姓名"></el-input>
      </el-form-item>
      <el-form-item label="讲师头衔">
        <el-select size="mini" v-model="teacherQuery.level" placeholder="讲师头衔">
          <el-option label="高级讲师" value="1"></el-option>
          <el-option label="首席讲师" value="2"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-date-picker
          size="mini"
          v-model="teacherQuery.begin"
          type="datetime"
          placeholder="选择截止时间"
          value-format="yyyy-MM-dd HH:mm:SS"
          defalut-time="00:00:00"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="选择截止时间">
        <el-date-picker
          size="mini"
          v-model="teacherQuery.end"
          type="datetime"
          placeholder="选择截止时间"
          value-format="yyyy-MM-dd HH:mm:SS"
          defalut-time="00:00:00"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button size="mini" type="primary" @click="getTeacherListPage()">查询</el-button>
      </el-form-item>
      <el-form-item>
        <el-button size="mini" type="primary" @click="resetData()">清空</el-button>
      </el-form-item>
    </el-form>

    <!-- 表格显示区域，：data="需要显示的数据，格式为一个元素为对象的数组" -->
    <el-table :data="teacherlist" border style="width: 100%">
      <el-table-column label="序号" width="70" align="center">
        <template slot-scope="scope">{{ (page - 1) * limit + scope.$index + 1 }}</template>
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="180"></el-table-column>
      <el-table-column label="头衔">
        <template slot-scope="scope">{{ scope.row.level === 1 ? "高级讲师" : "首席讲师" }}</template>
      </el-table-column>
      <el-table-column prop="intro" label="简介" width="180"></el-table-column>
      <el-table-column prop="career" label="讲师资历" width="180"></el-table-column>
      <el-table-column prop="gmtCreate" label="添加是时间"></el-table-column>
      <el-table-column prop="sort" label="排序"></el-table-column>
      <el-table-column fixed="right" label="操作" width="100">
        <template slot-scope="scope">
          <router-link :to="'/teacher/update/' + scope.row.id">
            <el-button @click="updateTeacherByID(scope.row.id)" type="text" size="mini">修改</el-button>
          </router-link>

          <el-button type="text" size="mini" @click="deleteTeacherByID(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页组件 -->
    <el-pagination
      background
      layout="prev, pager, next"
      :total="total"
      :current-page="page"
      :page-size="limit"
      @current-change="getTeacherListPage"
    ></el-pagination>
  </div>
</template>

<script>
import teacher from '@/api/edu/teacher'

export default {
  data() {
    return {
      teacherlist: [], // 查询讲师列表之后的返回值
      page: 1, // 当前页
      limit: 2, // 每页记录数
      teacherQuery: {}, // 封装查询条件对象
      total: 0,
    }
  },
  methods: {
    getTeacherListPage(page = 1) {
      console.log(this.page)
      this.page = page
      console.log(this.page)
      teacher
        .getTeacherListPage(this.page, this.limit, this.teacherQuery)
        .then((response) => {
          // 请求成功之后的返回结果
          console.log(response)
          this.teacherlist = response.data.rows
          this.total = response.data.total
        })
        .catch((error) => {
          // 请求失败之后的返回结果
          console.log(error)
        })
    },

    resetData() {
      this.teacherQuery = {}
      this.getTeacherListPage()
    },
    deleteTeacherByID(id) {
      this.$confirm('此操作将永久删除该讲师, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      })
        .then(() => {
          teacher
            .deleteTeacher(id)
            .then((response) => {
              this.$message({
                type: 'success',
                message: '删除成功!',
              })
              this.getTeacherListPage(this.page)
            })
            .catch((error) => {
              console.log(error)
            })
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除',
          })
        })
    },

    updateTeacherByID(id) {
      this.$router.push({ path: `/teacher/update/${id}` })
    },
  },

  created() {
    // 在页面完全渲染之前调用该方法，从后端获取数据
    this.getTeacherListPage()
  },
}
</script>
<style scoped>
.myClassList .info {
  width: 450px;
  overflow: hidden;
}
.myClassList .info .pic {
  width: 150px;
  height: 90px;
  overflow: hidden;
  float: left;
}
.myClassList .info .pic a {
  display: block;
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
}
.myClassList .info .pic img {
  display: block;
  width: 100%;
}
.myClassList td .info .title {
  width: 280px;
  float: right;
  height: 90px;
}
.myClassList td .info .title a {
  display: block;
  height: 48px;
  line-height: 24px;
  overflow: hidden;
  color: #00baf2;
  margin-bottom: 12px;
}
.myClassList td .info .title p {
  line-height: 20px;
  margin-top: 5px;
  color: #818181;
}
</style>