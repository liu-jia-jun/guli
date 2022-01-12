<template>
  <div class="app-container">
    <h2 style="text-align: center;">发布新课程</h2>

    <el-steps :active="2" process-status="wait" align-center style="margin-bottom: 40px;">
      <el-step title="填写课程基本信息" />
      <el-step title="创建课程大纲" />
      <el-step title="提交审核" />
    </el-steps>

    <el-form label-width="120px">
      <el-form-item>
        <el-button @click="previous">上一步</el-button>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="next">下一步</el-button>
      </el-form-item>

      <el-button type="text" @click="addChapterBtn">添加章节</el-button>
      <!-- 添加和修改章节表单 -->
      <el-dialog :visible.sync="dialogChapterFormVisible" title="添加章节">
        <el-form :model="chapter" label-width="120px">
          <el-form-item label="章节标题">
            <el-input v-model="chapter.title" />
          </el-form-item>
          <el-form-item label="章节排序">
            <el-input-number v-model="chapter.sort" :min="0" controls-position="right" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogChapterFormVisible=false">取 消</el-button>
          <el-button type="primary" @click="saveOrUpdate">确 定</el-button>
        </div>
      </el-dialog>
      <!-- 添加和修改课时表单 -->
      <el-dialog :visible.sync="dialogVideoFormVisible" title="添加课时">
        <el-form :model="video" label-width="120px">
          <el-form-item label="课时标题">
            <el-input v-model="video.title" />
          </el-form-item>
          <el-form-item label="课时排序">
            <el-input-number v-model="video.sort" :min="0" controls-position="right" />
          </el-form-item>
          <el-form-item label="是否免费">
            <el-radio-group v-model="video.isFree">
              <el-radio :label="true">免费</el-radio>
              <el-radio :label="false">默认</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="上传视频">
            <!-- TODO -->
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogVideoFormVisible = false">取 消</el-button>
          <el-button :disabled="saveVideoBtnDisabled" type="primary" @click="saveOrUpdateVideo">确 定</el-button>
        </div>
      </el-dialog>
      <!-- 章节 -->
      <ul class="chanpterList">
        <li v-for="chapter in chapterNestedList" :key="chapter.id">
          <p>
            {{ chapter.title }}
            <span class="acts">
              <el-button type="text" @click="addVideoBtn(chapter.id)">添加课时</el-button>
              <el-button style type="text" @click="editChapter(chapter.id)">编辑</el-button>
              <el-button type="text" @click="removeChapter(chapter.id)">删除</el-button>
            </span>
          </p>

          <!-- 视频 -->
          <ul class="chanpterList videoList">
            <li v-for="video in chapter.children" :key="video.id">
              <p>
                {{ video.title }}
                <span class="acts">
                  <el-button type="text" @click="editVideo(video.id)">编辑</el-button>
                  <el-button type="text" @click="deleteVideo(video.id)">删除</el-button>
                </span>
              </p>
            </li>
          </ul>
        </li>
      </ul>
    </el-form>
  </div>
</template>

<script>
import chapter from '@/api/edu/chapter'
import video from '@/api/edu/video'
export default {
  data() {
    return {
      video: {
        // 课时对象
        title: '',
        sort: 0,
        isFree: 0,
        videoSourceId: '',
      },
      dialogVideoFormVisible: false,
      saveBtnDisabled: false, // 保存按钮是否禁用
      saveVideoBtnDisabled: false,
      courseId: '', // 所属课程
      chapterNestedList: [], // 章节嵌套课时列表
      dialogChapterFormVisible: false, //是否显示章节表单

      chapter: {
        // 章节对象
        title: '',
        sort: 0,
      },
    }
  },
  created() {
    this.courseId = this.$route.params.id
    // 根据id获取课程基本信息
    this.fetchChapterNestedListByCourseId()
  },

  methods: {
    // +++++++++++++++++++++++++++++基于小节操作+++++++++++++++++++++++++++

    deleteVideo(id) {
      this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      })
        .then(() => {
          return video.deleteVideo(id)
        })
        .then(() => {
          this.fetchChapterNestedListByCourseId()
          this.$message({
            type: 'success',
            message: '删除成功!',
          })
        })
        .catch((response) => {
          // 失败
          if (response === 'cancel') {
            this.$message({
              type: 'info',
              message: '已取消删除',
            })
          } else {
            this.$message({
              type: 'error',
              message: response.message,
            })
          }
        })
    },
    editVideo(id) {
      video.getVideo(id).then((response) => {
        this.video = response.data.video
        this.dialogVideoFormVisible = true
      })
    },
    addVideoBtn(id) {
      this.dialogVideoFormVisible = true
      this.video = {}
      this.video.chapterId = id
      console.log(this.video)
    },
    saveVideo() {
      this.video.courseId = this.courseId

      video.addVideo(this.video).then((response) => {
        this.$message({
          type: 'success',
          message: '保存成功!',
        })
        this.saveVideoHelp()
      })
    },

    updateVideo() {
      video.updateVideo(this.video).then((response) => {
        this.$message({
          type: 'success',
          message: '修改成功!',
        })
        this.saveVideoHelp()
      })
    },

    saveOrUpdateVideo() {
      if (!this.video.id) {
        this.saveVideo()
      } else {
        this.updateVideo()
      }
    },
    saveVideoHelp() {
      this.dialogVideoFormVisible = false // 如果保存成功则关闭对话框

      this.fetchChapterNestedListByCourseId()
    },
    // +++++++++++++++++++++++++++++基于章节操作+++++++++++++++++++++++++++
    removeChapter(chapterId) {
      this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      })
        .then(() => {
          return chapter.removeById(chapterId)
        })
        .then(() => {
          this.fetchChapterNestedListByCourseId()
          this.$message({
            type: 'success',
            message: '删除成功!',
          })
        })
        .catch((response) => {
          // 失败
          if (response === 'cancel') {
            this.$message({
              type: 'info',
              message: '已取消删除',
            })
          } else {
            this.$message({
              type: 'error',
              message: response.message,
            })
          }
        })
    },
    addChapterBtn() {
      this.chapter = {}
      this.dialogChapterFormVisible = true
    },
    editChapter(id) {
      chapter.getById(id).then((response) => {
        this.chapter = response.data.chapter
        this.dialogChapterFormVisible = true
      })
    },
    updateChapter() {
      this.chapter.courseId = this.courseId

      chapter.updateChapter(this.chapter).then((response) => {
        this.$message({
          type: 'success',
          message: '修改成功!',
        })
        // 修改成功之后的操作
        this.saveHelp()
      })
    },
    saveChapter() {
      this.chapter.courseId = this.courseId

      chapter.save(this.chapter).then((response) => {
        this.$message({
          type: 'success',
          message: '保存成功!',
        })
        // 保存成功之后的操作
        this.saveHelp()
      })
    },
    saveOrUpdate() {
      if (!this.chapter.id) {
        this.saveChapter()
      } else {
        this.updateChapter()
      }
    },

    saveHelp() {
      this.dialogChapterFormVisible = false // 如果保存成功则关闭对话框
      this.chapter.title = ''
      this.fetchChapterNestedListByCourseId()
    },

    fetchChapterNestedListByCourseId() {
      chapter.getNestedTreeList(this.courseId).then((response) => {
        this.chapterNestedList = response.data.items
      })
    },
    previous() {
      console.log('previous')
      this.$router.push({ path: '/edu/course/info/' + this.courseId })
    },

    next() {
      console.log('next')
      this.$router.push({ path: '/edu/course/publish/' + this.courseId })
    },
  },
}
</script>

<style scoped>
/* 列表样式 */
.chanpterList {
  position: relative;
  list-style: none;
  margin: 0;
  padding: 0;
}
.chanpterList li {
  position: relative;
}
.chanpterList p {
  float: left;
  font-size: 20px;
  margin: 10px 0;
  padding: 10px;
  height: 70px;
  line-height: 50px;
  width: 100%;
  border: 1px solid #ddd;
}
.chanpterList .acts {
  float: right;
  font-size: 14px;
}

.videoList {
  padding-left: 50px;
}
.videoList p {
  float: left;
  font-size: 14px;
  margin: 10px 0;
  padding: 10px;
  height: 50px;
  line-height: 30px;
  width: 100%;
  border: 1px dotted #ddd;
}
</style>
