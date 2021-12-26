<template>
  <div class="app-container">讲师列表</div>
</template>

<script>
import teacher from "@/api/edu/teacher";

export default {
  data() {
    return {
      list: [], // 查询讲师列表之后的返回值
      page: 1, // 当前页
      limit: 10, // 每页记录数
      teacherQuery: {}, // 封装查询条件对象
    };
  },
  methods: {
    getTeacherListPage() {
      teacher
        .getTeacherListPage(this.page, this.limit, this.teacherQuery)
        .then((response) => {
          // 请求成功之后的返回结果
          console.log(response);
        })
        .catch((error) => {
          // 请求失败之后的返回结果
          console.log(error);
        });
    },
  },
  created() {
    // 在页面完全渲染之前调用该方法，从后端获取数据
    this.getTeacherListPage();
  },
};
</script>
