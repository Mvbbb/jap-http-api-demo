<template>
  <div class="home">
    <img alt="logo" src="https://justauth.plus/logo.png">
    <div class="content">

      <h1> JAP HTTP API 演示 demo</h1>

      <div class="dev_class">
        <h2>开发者配置 HttpApiConfig</h2>
        <set></set>
        <el-divider></el-divider>
        <h2>用户登录测试</h2>
        <user-login></user-login>
      </div>


      <div class="doc_class">
        <doc></doc>
      </div>


    </div>

  </div>
</template>


<script>
import UserLogin from '../component/UserLogin.vue'
import Set from '../component/Set.vue'
import Doc from '../component/Doc.vue'

export default {
  name: 'Home',
  components: {
    UserLogin,
    Set,
    Doc
  }
}

</script>
<style>
img {
  width: 7%;
}

.dev_class {
  float: left;
  width: 30%;
  margin-left: 4%;
}

.doc_class {
  margin-left: 40%;
}

li {
  text-align: left;
}

</style>
