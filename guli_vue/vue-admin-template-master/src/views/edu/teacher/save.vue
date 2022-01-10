<template>
  <div class="app-container">
    <h3>添加讲师</h3>
    <el-form ref="form" :model="teacher" label-width="80px">
      <!-- 讲师头像 -->
      <el-form-item label="讲师头像">
        <!-- 头衔缩略图 -->
        <pan-thumb :image="teacher.avatar" />
        <!-- 文件上传按钮 -->
        <el-button
          type="primary"
          icon="el-icon-upload"
          @click="imagecropperShow = true"
          >上传头像
        </el-button>

        <!--
v-show：是否显示上传组件
:key：类似于id，如果一个页面多个图片上传控件，可以做区分
:url：后台上传的url地址
@close：关闭上传组件
@crop-upload-success：上传成功后的回调 -->
        <image-cropper
          v-show="imagecropperShow"
          :width="300"
          :height="300"
          :key="imagecropperKey"
          :url="BASE_API + '/eduoss/fileoss/uploadFile'"
          field="multipartFile"
          @close="close"
          @crop-upload-success="cropSuccess"
        />
      </el-form-item>
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
import ImageCropper from "@/components/ImageCropper";
import PanThumb from "@/components/PanThumb";
export default {
  components: { ImageCropper, PanThumb },
  data() {
    return {
      teacher: {
        avatar:"https://liu-jia-jun.oss-cn-beijing.aliyuncs.com/2022/01/10/914ac2681dcd4e08af406a96f17f047dmultipartFile.png"
      },
      BASE_API: process.env.BASE_API, // 获取接口的API地址
      imagecropperShow: false, // 是否显示上传组件
      imagecropperKey: 0, // 上传组件id值
    };
  },

  watch: {
    // 路由监听，路由发生变化时，该方法就会执行
    $route(to, from) {
      this.init();
    },
  },
  methods: {
    close() {
      // 关闭上传组件的方法
      this.imagecropperShow = false;
      this.imagecropperKey = this.imagecropperKey + 1;
    },
    cropSuccess(data) {
      // 上传成功之后的方法
      this.imagecropperShow = false;
      // 上传时候，在接口中返回图片地址，去保存到数据库中
      this.imagecropperShow = false;
      this.teacher.avatar = data.url;
      // 上传成功后，重新打开上传组件时初始化组件，否则显示上一次的上传结果
      this.imagecropperKey = this.imagecropperKey + 1;
    },
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
