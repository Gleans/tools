<template>
  <div>
    <el-tabs :tab-position="tabPosition" style="height: 100%" @tab-click="handleClick">
      <el-tab-pane :label="tabs.name" :key="tabs.id" v-for="tabs in tabName">{{tabs.name}}</el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
  import {postRequest} from '@/utils/api'
  import {getRequest} from '@/utils/api'

  export default {
    name: 'HelloWorld',
    data() {
      return {
        tabName: [
          {
            id: 1,
            name: '全部'
          },
          {
            id: 2,
            name: '段子'
          },
          {
            id: 3,
            name: '内涵图'
          },
          {
            id: 4,
            name: '动态图'
          },
          {
            id: 5,
            name: '短视频'
          }],
        params:{
          type: 1,
          page: 1
        },
        tabPosition: 'left'
      }
    },
    created() {
      this.init()
    },
    methods: {
       init(){
        /* this.getRequest("/duanzi",this.params).then(res=> {
           console.log(res.data)
         })*/
       },
      handleClick(tab, event) {
        console.log(tab, event);
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>

</style>
