<template>
  <div class="app-container">
    <h3>添加讲师</h3>
    <el-form ref="form" :model="teacher" label-width="80px">
      <el-form-item label="讲师名称">
        <el-input v-model="teacher.name"></el-input>
      </el-form-item>
      <el-form-item label="讲师排序">
        <el-input v-model="teacher.sort" type="number" :min="0"></el-input>
      </el-form-item>
      <el-form-item label="讲师头衔">
        <el-select v-model="teacher.level" placeholder="讲师头衔">
          <el-option label="高级讲师" value="1"></el-option>
          <el-option label="首席讲师" value="2"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="讲师资历">
        <el-input v-model="teacher.career"></el-input>
      </el-form-item>
      <el-form-item label="讲师简介">
        <el-input v-model="teacher.intro" type="textarea" :rows="10"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="saveOrUpdate()">立即创建</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import teacher from "@/api/edu/teacher";

export default {
  data() {
    return {
      teacher: {},
    };
  },

  watch:{
    // 路由监听，路由发生变化时，该方法就会执行
    $route(to,from){
      this.init()
    }
  },
  methods: {
    init() {
      if (this.$route.params && this.$route.params.id) {
        this.getInfo(this.$route.params.id);
      } else {
        this.teacher = {};
      }
    },





    getInfo(id) {
      teacher.getInfo(id).then((response) => {
        this.teacher = response.data.teacher;
      });
    },

    saveOrUpdate() {
      // 根据teacher对象中是否含有id属性，添加时id由数据库生成所有没有该属性，修改时，执行了getInfo方法所以会含有id属性
      if (this.teacher.id) {
        // 修改
        this.updateTeacher();
      } else {
        // 添加
        this.saveTeacher();
      }
    },

    updateTeacher() {
      teacher.updateTeacherInfo(this.teacher).then(
        (response) => {
          this.$message({
            type: "success",
            message: "修改成功",
          });
          this.$router.push({ path: "/teacher/list" });
        },
        (error) => {
          this.$message({
            type: "info",
            message: "修改失败",
          });
        }
      );
    },
    saveTeacher() {
      teacher.addTeacher(this.teacher).then(
        (response) => {
          this.$message({
            type: "success",
            message: "添加成功",
          });
          this.$router.push({ path: "/teacher/list" });
        },
        (error) => {
          this.$message({
            type: "info",
            message: "添加失败",
          });
        }
      );
    },
  },

  created() {
    this.init();
  },
};
</script>
