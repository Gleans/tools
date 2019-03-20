<template>
  <div>
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane :label="tab.name" :name="tab.id.toString()" :key="tab.id" v-for="tab in tabName">
        <el-row class="context">
          <el-col :span="7" v-for="(item,index) in tabData" :key="index.toString()" :offset="1">
            <el-card :body-style="{ padding: '0px' }">
              <img :src="item.picInfo.length>0?item.picInfo[0].url:'http://ws3.sinaimg.cn/large/9150e4e5ly1ffcgny3540j20go0go0tp.jpg'"
                width="50" height="50" class="image"/>
              <div style="padding: 14px;">
                <span style="display: block;">{{item.title!=null?item.title.substr(0,10)+'...':null}}</span><br>
                <div class="bottom clearfix">
                  <time class="time">{{item.ptime}}</time>
                  <a :href="item.link" target="_blank" style="float: right">
                    <el-button size="mini" plain>访问</el-button>
                  </a>
                </div>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </el-tab-pane>

    </el-tabs>


  </div>
</template>

<script>
  import {postRequest} from '../utils/api'

  export default {
    name: "home",
    data() {
      return {
        activeName: '1',
        tabData: [],
        dataX: [],
        tabName: [
          {
            id: 1,
            name: '科技'
          },
          {
            id: 2,
            name: '汽车'
          },
          {
            id: 3,
            name: '财经'
          },
          {
            id: 4,
            name: '体育'
          },
          {
            id: 5,
            name: '推荐'
          },
          {
            id: 6,
            name: '环球'
          },
          {
            id: 7,
            name: '娱乐'
          },
          {
            id: 8,
            name: '头条'
          }
        ]
      }
    },
    methods: {},
    created() {
      this.init()
    },
    methods: {
      init() {
        this.getRequest("/news").then(res => {
          this.dataX = res.data.data
          this.tabData=this.dataX.tech
        })
      },
      handleClick(tab, event) {
        switch (tab.name) {
          case '1':this.tabData=this.dataX.tech
            break
          case '2':this.tabData=this.dataX.auto
            break
          case '3':this.tabData=this.dataX.money
            break
          case '4':this.tabData=this.dataX.sports
            break
          case '5':this.tabData=this.dataX.dy
            break
          case '6':this.tabData=this.dataX.war
            break
          case '7':this.tabData=this.dataX.ent
            break
          case '8':this.tabData=this.dataX.toutiao
            break
        }
        console.log(tab, event)
      }
    }
  }
</script>
<style>
  .time {
    font-size: 13px;
    color: #999;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 100%;
    height: 300px;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }

  .el-card {
    margin-top: 10px;
  }

</style>
