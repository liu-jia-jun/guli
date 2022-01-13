<template>
  <div class="app-container">
    <!-- 查询条件输入 -->
    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <!-- 所属分类：级联下拉列表 -->
      <!-- 一级分类 -->
      <el-form-item label="课程类别">
        <el-select
          v-model="searchObj.subjectParentId"
          placeholder="请选择"
          @change="subjectLevelOneChanged"
        >
          <el-option
            v-for="subject in subjectNestedList"
            :key="subject.id"
            :label="subject.title"
            :value="subject.id"
          />
        </el-select>

        <!-- 二级分类 -->
        <el-select v-model="searchObj.subjectId" placeholder="请选择">
          <el-option
            v-for="subject in subSubjectList"
            :key="subject.id"
            :label="subject.title"
            :value="subject.id"
          />
        </el-select>
      </el-form-item>

      <!-- 标题 -->
      <el-form-item>
        <el-input v-model="searchObj.title" placeholder="课程标题" />
      </el-form-item>
      <!-- 讲师 -->
      <el-form-item>
        <el-select v-model="searchObj.teacherId" placeholder="请选择讲师">
          <el-option
            v-for="teacher in teacherList"
            :key="teacher.id"
            :label="teacher.name"
            :value="teacher.id"
          />
        </el-select>
      </el-form-item>

      <el-button type="primary" icon="el-icon-search" @click="searchFetchData()">查询</el-button>
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>
    <!-- 表格显示区域，：data="需要显示的数据，格式为一个元素为对象的数组" -->

    <!-- 表格 -->
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="数据加载中"
      border
      fit
      highlight-current-row
      row-class-name="myClassList"
    >
      <el-table-column label="序号" width="70" align="center">
        <template slot-scope="scope">{{ (page - 1) * limit + scope.$index + 1 }}</template>
      </el-table-column>

      <el-table-column label="课程信息" width="470" align="center">
        <template slot-scope="scope">
          <div class="info">
            <div class="pic">
              <img :src="scope.row.cover" alt="scope.row.title" width="150px" />
            </div>
            <div class="title">
              <a href>{{ scope.row.title }}</a>
              <p>{{ scope.row.lessonNum }}课时</p>
            </div>
          </div>
        </template>
      </el-table-column>

      <el-table-column label="创建时间" align="center">
        <template slot-scope="scope">{{ scope.row.gmtCreate.substr(0, 10) }}</template>
      </el-table-column>
      <el-table-column label="发布时间" align="center">
        <template slot-scope="scope">{{ scope.row.gmtModified.substr(0, 10) }}</template>
      </el-table-column>
      <el-table-column label="价格" width="100" align="center">
        <template slot-scope="scope">
          {{ Number(scope.row.price) === 0 ? '免费' :
          '¥' + scope.row.price.toFixed(2) }}
        </template>
      </el-table-column>
      <el-table-column prop="buyCount" label="付费学员" width="100" align="center">
        <template slot-scope="scope">{{ scope.row.buyCount }}人</template>
      </el-table-column>

      <el-table-column label="操作" width="150" align="center">
        <template slot-scope="scope">
          <router-link :to="'/edu/course/info/'+scope.row.id">
            <el-button type="text" size="mini" icon="el-icon-edit">编辑课程信息</el-button>
          </router-link>
          <router-link :to="'/edu/course/chapter/'+scope.row.id">
            <el-button type="text" size="mini" icon="el-icon-edit">编辑课程大纲</el-button>
          </router-link>
          <el-button type="text" size="mini" icon="el-icon-delete">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination
      :current-page="page"
      :page-size="limit"
      :total="total"
      style="padding: 30px 0; text-align: center;"
      layout="total, prev, pager, next, jumper"
      @current-change="fetchData"
    />
  </div>
</template>

<script>
import course from '@/api/edu/course'

export default {
  data() {
    return {
      searchObj: {}, // 查询对象封装
      list: [],
      page: 1,
      limit: 5,
      total: 0,
      subjectNestedList: [],
      subSubjectList: [],
      teacherList: [],
      listLoading: true, // 在表格数据加载时产生的动画效果
    }
  },
  methods: {
    // 初始化教师列表
    initTeacherList() {
      teacher.getList().then((response) => {
        this.teacherList = response.data.items
      })
    },
    searchFetchData() {
      course
        .searchCourse(this.page, this.limit, this.searchObj)
        .then((response) => {
          this.list = response.data.list
          this.total = response.data.total

          this.page = response.data.current
        })
    },
    // 二级分类查询
    subjectLevelOneChanged() {},
    // 条件查询
    fetchData() {},
    // 查询对象数据清空
    resetData() {
      this.searchObj = {}
      this.getAllCourse()
    },
    // 查询所有的课程信息
    getPageListCourse() {
      this.listLoading = true
      course.getPageListCourse(this.page, this.limit).then((response) => {
        this.list = response.data.list
        this.total = response.data.total
        this.page = response.data.current
        this.listLoading = false
        console.log(this.list)
      })
    },
  },

  created() {
    // 页面渲染时调用
    this.getPageListCourse()
  },
}
</script>
