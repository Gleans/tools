<template>
  <el-menu
    :default-active="$route.fullPath"
    class="el-menu-vertical-demo"
    router
    background-color="#2c3e50"
    text-color="#fff"
    active-text-color="#ffd04b"
    style="width:100%;border:none"
    unique-opened
    collapse-transition>
    <!--<template v-for="(item,index) in navData" v-if="!item.hidden">--->
    <template v-for="(item,index) in navData">
      <el-submenu :index="item.path" v-if="item.key" :key="index">
        <template slot="title">
          <i :class="item.iconcls"></i>
          <span>{{item.name}}</span>
        </template>
        <!--<el-menu-item v-for="child in item.children" v-if="!child.hidden" :index="child.path" :key="child.path">-->
        <el-menu-item v-for="child in item.children" :index="child.path" :key="child.path">
          <template slot="title">
            <i :class="child.iconcls"></i>
            <span>{{child.name}}</span>
          </template>
        </el-menu-item>
      </el-submenu>
      <el-menu-item :index="item.path" v-else>
        <i :class="item.iconcls"></i>
        <span slot="title">{{item.name}}</span>
      </el-menu-item>
    </template>
  </el-menu>
</template>


<script>
  export default {
    data() {
      return {
        navData: []
      }
    },
    watch: {
      $route() {
        this.navData = JSON.parse(sessionStorage.getItem('routes'))
      }
    },
    created(){
      this.navData = JSON.parse(sessionStorage.getItem('routes'))
    }
  }
</script>
<style>

</style>
