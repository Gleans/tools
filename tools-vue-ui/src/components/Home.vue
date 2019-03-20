<template>
  <div>
    <h1>hello</h1>
    <el-button @click="init()" plain >清除路由</el-button>
  </div>
</template>

<script>
  export default {
    name: "Home",
    methods: {
      init() {
        sessionStorage.removeItem("routes")
        // this.$router.go(0);
        // location.reload()
        // location.reload()
        console.log("success")
      }
    }
  }
</script>

<style scoped>

</style>
