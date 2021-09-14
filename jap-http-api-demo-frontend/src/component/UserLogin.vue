<template>
  <div>
    <div v-loading="logining" element-loading-text="登录中">
      <el-form lable-width="0px" class="login_form">
        <el-form-item label="用户名">
          <el-input v-model="loginForm.username" clearable></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="loginForm.password" clearable show-password></el-input>
        </el-form-item>
        <el-form-item class="btns">
          <el-button type="primary" @click="login()">登录</el-button>
        </el-form-item>
      </el-form>
    </div>

    <div>
      <el-dialog
        title="登录结果"
        :visible.sync="dialogVisible"
        width="30%"
      >
        <p class="json_class">
          <json-view
            :data="login_reslut"/>
        </p>
        <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
        </span>

      </el-dialog>
    </div>
  </div>

</template>

<script>
import axios from 'axios'
import jsonView from 'vue-json-views'

export default {
  components: {
    jsonView
  },
  data() {
    return {
      loginForm: {
        username: '',
        password: ''
      },
      dialogVisible: false,
      login_reslut: '',
      logining: false
    }
  },
  methods: {
    login() {
      console.log("登录")
      this.logining = true
      let authInfoField = this.$common.authInfoField
      console.log(authInfoField)
      let axios_req = ''
      if (authInfoField == 'header') {
        axios_req = axios({
          method: "post",
          url: "/login",
          headers: {
            "username": this.loginForm.username,
            "password": this.loginForm.password
          }
        });
      } else if (authInfoField == 'params') {
        axios_req = axios({
          method: "get",
          url: "/login",
          params: this.loginForm
        });
      } else if (authInfoField == 'body') {
        axios_req = axios({
          method: "post",
          url: "/login",
          data: JSON.stringify(this.loginForm)
        });
      }else{
        this.logining=false
        return ;
      }

      axios_req.then((res) => {
        this.logining = false
        this.dialogVisible = true
        this.login_reslut = res.data
        this.login_reslut.data = JSON.parse(res.data.data)
        this.login_reslut.data.data= JSON.parse(res.data.data.data)
      })
    }
  }
}
</script>
<style>
.json_class{
  text-align: left;
}
</style>
