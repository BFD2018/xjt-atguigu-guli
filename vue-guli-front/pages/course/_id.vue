<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- /课程详情 开始 -->
    <section class="container">
      <section class="path-wrap txtOf hLh30">
        <a href="#" title class="c-999 fsize14">首页</a>
        \
        <a href="#" title class="c-999 fsize14">{{courseWebVo.subjectLevelOne}}</a>
        \
        <span class="c-333 fsize14">{{courseWebVo.subjectLevelTwo}}</span>
      </section>
      <div>
        <article class="c-v-pic-wrap" style="height: 357px;">
          <section class="p-h-video-box" id="videoPlay">
            <img height="357px" :src="courseWebVo.cover" :alt="courseWebVo.title" class="dis c-v-pic">
          </section>
        </article>
        <aside class="c-attr-wrap">
          <section class="ml20 mr15">
            <h2 class="hLh30 txtOf mt15">
              <span class="c-fff fsize24">{{courseWebVo.title}}</span>
            </h2>
            <section class="c-attr-jg">
              <span class="c-fff">价格：</span>
              <b class="c-yellow" style="font-size:24px;">￥{{courseWebVo.price}}</b>
            </section>
            <section class="c-attr-mt c-attr-undis">
              <span class="c-fff fsize14">主讲： {{courseWebVo.teacherName}}&nbsp;&nbsp;&nbsp;</span>
            </section>
            <section class="c-attr-mt of">
              <span class="ml10 vam">
                <em class="icon18 scIcon"></em>
                <a class="c-fff vam" title="收藏" href="#">收藏</a>
              </span>
            </section>
            <section v-if="isbuy || Number(courseWebVo.price) === 0" class="c-attr-mt">
              <a href="javascript:void(0);" title="立即观看" class="comm-btn c-btn-3">立即观看</a>
            </section>
            <section v-else class="c-attr-mt">
              <a @click="createOrders" href="javascript:void(0);" title="立即购买" class="comm-btn c-btn-3">立即购买</a>
            </section>
          </section>
        </aside>
        <aside class="thr-attr-box">
          <ol class="thr-attr-ol">
            <li>
              <p>&nbsp;</p>
              <aside>
                <span class="c-fff f-fM">购买数</span>
                <br>
                <h6 class="c-fff f-fM mt10">{{courseWebVo.buyCount}}</h6>
              </aside>
            </li>
            <li>
              <p>&nbsp;</p>
              <aside>
                <span class="c-fff f-fM">课时数</span>
                <br>
                <h6 class="c-fff f-fM mt10">20</h6>
              </aside>
            </li>
            <li>
              <p>&nbsp;</p>
              <aside>
                <span class="c-fff f-fM">浏览数</span>
                <br>
                <h6 class="c-fff f-fM mt10">501</h6>
              </aside>
            </li>
          </ol>
        </aside>
        <div class="clear"></div>
      </div>
      <!-- /课程封面介绍 -->
      <div class="mt20 c-infor-box">
        <article class="fl col-7">
          <section class="mr30">
            <div class="i-box">

              <div>
                <section id="c-i-tabTitle" class="c-infor-tabTitle c-tab-title">
                  <a name="c-i" class="current" title="课程详情">课程详情</a>
                </section>
              </div>
              <article class="ml10 mr10 pt20">
                <!-- /课程介绍 开始-->
                <div>
                  <h6 class="c-i-content c-infor-title">
                    <span>课程介绍</span>
                  </h6>
                  <div class="course-txt-body-wrap">
                    <section class="course-txt-body">
                      <p v-html="courseWebVo.description">{{courseWebVo.description}}</p>
                    </section>
                  </div>
                </div>
                <!-- /课程介绍 结束-->

                <div class="clear"></div>
                <el-divider></el-divider>
                <!-- /课程大纲 开始-->
                <div class="mt50">
                  <h6 class="c-g-content c-infor-title">
                    <span>课程大纲</span>
                  </h6>
                  <section class="mt20">
                    <div class="lh-menu-wrap">
                      <menu id="lh-menu" class="lh-menu mt10 mr10">
                        <ul>
                          <!-- 文件目录 -->
                          <li class="lh-menu-stair" v-for="chapter in chapterVideoList" :key="chapter.id">
                            <a href="javascript: void(0)" :title="chapter.title" class="current-1">
                              <em class="lh-menu-i-1 icon18 mr10"></em>{{chapter.title}}
                            </a>

                            <ol class="lh-menu-ol" style="display: block;">
                              <li class="lh-menu-second ml30" v-for="video in chapter.children" :key="video.id">
                                <!--                                <div>{{video}}</div>-->
                                <a :href="isbuy==true?'/player/'+video.videoSourceId:'#'" target="_blank">
                                  <span class="fr" v-if="isbuy">
                                    <i class="free-icon vam mr10">免费试听</i>
                                  </span>
                                  <span class="fr" v-else>
                                    <i class="free-icon vam mr10">购买后观看</i>
                                  </span>
                                  <em class="lh-menu-i-2 icon16 mr5">&nbsp;</em>{{video.title}}
                                </a>
                              </li>

                            </ol>

                          </li>
                        </ul>
                      </menu>
                    </div>
                  </section>
                </div>
                <!-- /课程大纲 结束-->

                <div class="clear"></div>
                <el-divider></el-divider>

                <!-- /课程评论 开始-->
                <div>
                  <h6 class="c-i-content c-infor-title">
                    <span>课程评论</span>
                  </h6>
                  <div class="btn-comment">
                    <el-button type="primary" size="mini" style="float: right;"
                               :class="{disable:isNotUserLogin}"
                               @click="dialogVisible = true">我要评论
                    </el-button>
                  </div>
                  <div class="clear"></div>
                  <div class="course-comments-wrap">
                    <div class="reviews-item" v-for="comment in commentList"
                         :key="comment.id" style="display: flex;padding: 10px;border-bottom: 1px solid #a4b0be;">
                      <div class="review-left" style="width: 100px;">
                        <img :src="comment.avatar" title="" style="width: 100px;height: 100px;border-radius: 50%;">
                      </div>
                      <div class="reviews-text"
                           style="flex: 1;margin-left: 30px;border-left: 2px solid #ddd;padding: 15px;">
                        <div class="reviews-text__nickname">
                          <a href="javascript:;" target="_blank" class="link-dark">@{{comment.nickname}}</a>
                          <span style="font-size: 12px;margin-left: 15px;"><i class="el-icon-date"></i>{{comment.gmtCreate}}</span>
                        </div>

                        <div class="review__content" style="padding: 10px 0;font-size: 14px;"
                             v-html="comment.content"></div>
                      </div>
                    </div>
                  </div>
                </div>
                <!-- /课程评论 结束-->
              </article>
            </div>
          </section>
        </article>

        <!--右侧 主讲讲师 开始-->
        <aside class="fl col-3">
          <div class="i-box">
            <div>
              <section class="c-infor-tabTitle c-tab-title">
                <a title href="javascript:void(0)">主讲讲师</a>
              </section>
              <section class="stud-act-list">
                <ul style="height: auto;">
                  <li>
                    <div class="u-face">
                      <a href="#">
                        <img :src="courseWebVo.avatar" width="50" height="50" alt>
                      </a>
                    </div>
                    <section class="hLh30 txtOf">
                      <a class="c-333 fsize16 fl" href="#">{{courseWebVo.teacherName}}</a>
                    </section>
                    <section class="hLh20 txtOf">
                      <span class="c-999">{{courseWebVo.intro}}</span>
                    </section>
                  </li>
                </ul>
              </section>
            </div>
          </div>
        </aside>
        <!--右侧 主讲讲师 结束-->

        <div class="clear"></div>
      </div>
    </section>
    <!-- /课程详情 结束 -->

    <!--课程评论弹窗 开始-->
    <el-dialog
      title="添加评论"
      :visible.sync="dialogVisible"
      width="50%">
      <el-input type="textarea" v-model="commentObj.content" rows="6"></el-input>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveComment">确 定</el-button>
      </span>
    </el-dialog>
  </div>

</template>

<script>
  import courseApi from '@/api/course'
  import ordersApi from '@/api/orders'

  import jscookie from "js-cookie"

  export default {
    asyncData({params, error}) {
      return {courseId: params.id}
    },
    data() {
      return {
        commentList: [],
        dialogVisible: false,
        commentObj: {},   //评论内容
        isNotUserLogin: true,    //默认没有登录

        courseWebVo: {},
        chapterVideoList: [],
        isbuy: false,   //是否已购买课程
      }
    },
    mounted() {
      this.getAllComments();

      //初始化要提交的评论内容
      this.initCommentObj();

    },
    methods: {
      initCommentObj() {
        //先判断用户是否已登录
        let guli_ucenter = jscookie.get("guli_ucenter")
        if (guli_ucenter == "" || guli_ucenter == null) {
          this.$notify({
            title: '提示',
            message: "没有登录不能评论，请先去登录！"
          });
          return
        }
        this.isNotUserLogin = false;
        let guli_user = JSON.parse(guli_ucenter)
        // console.log(guli_user);
        // console.log(this.courseWebVo);

        this.commentObj = {
          "courseId": this.courseId,
          "teacherId": this.courseWebVo.teacherId,
          "memberId": guli_user.id,
          "nickname": guli_user.nickname,
          "avatar": guli_user.avatar,
        }
      },
      //查询课程详情信息
      initCourseInfo() {
        courseApi.getCourseInfo(this.courseId)
          .then(response => {
            console.log(response);
            this.courseWebVo = response.data.data.courseWebVo,
              this.chapterVideoList = response.data.data.chapterVideoList,
              this.isbuy = response.data.data.isBuy
          })
      },
      //立即购买--->生成订单
      createOrders() {
        ordersApi.createOrders(this.courseId).then(response => {
          console.log(response);
          //获取返回订单号
          //生成订单之后，跳转订单显示页面
          this.$router.push({path: '/orders/' + response.data.data.orderNo})
        })
      },
      //获取课程所有评论
      getAllComments() {
        courseApi.getCourseComments(this.courseId).then(res => {
          //console.log(res);
          this.commentList = res.data.data.commentList;
        })
      },

      saveComment() {
        console.log(this.commentObj);
        //提交评论
        courseApi.addCourseComment(this.commentObj).then(res => {
          this.dialogVisible = false;
          console.log(res);
          this.$message.success("评论成功");

          this.commentObj.content = "";
          this.getAllComments();
        })
      },
    },
    created() {
      this.initCourseInfo();
    }
  };
</script>

<style>
  .editor {
    line-height: normal !important;
    height: 300px;
    margin-bottom: 30px;
  }
</style>
