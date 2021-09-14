<template>
  <div>
    <div>
      <el-form>

        <el-form-item label="第三方认证方式" >
          <el-radio-group v-model="setting.authSchema" @change="chooseAuthSchema" >
            <el-radio :label='"basic"'>BASIC</el-radio>
            <el-radio :label='"digest"'>DIGEST</el-radio>
            <el-radio :label='"bearer"'>BEARER</el-radio>
          </el-radio-group>
        </el-form-item>


        <el-form-item label="第三方 Bearer Token 签发请求地址" v-show="this.showForBearerTokenEnum">
          <el-input v-model="setting.bearerTokenIssueUrl" placeholder="请输入url" clearable></el-input>
        </el-form-item>

        <el-form-item label="Bearer 认证预请求方式" v-show="this.showForBearerTokenEnum">
          <el-radio v-model="setting.forBearerTokenEnum" label="by_header">BY_HEADER</el-radio>
          <el-radio v-model="setting.forBearerTokenEnum" label="by_params">BY_PARAMS</el-radio>
          <el-radio v-model="setting.forBearerTokenEnum" label="by_body">BY_BODY</el-radio>
          <el-radio v-model="setting.forBearerTokenEnum" label="by_basic">BY_BASIC</el-radio>
          <el-radio v-model="setting.forBearerTokenEnum" label="by_digest">BY_DIGEST</el-radio>
        </el-form-item>

        <el-form-item label="第三方HTTP请求方式">
          <el-radio v-model="setting.httpMethod" label="get">GET</el-radio>
          <el-radio v-model="setting.httpMethod" label="post">POST</el-radio>
        </el-form-item>

        <el-form-item inline label="第三方登录地址">
          <el-input v-model="setting.loginUrl" placeholder="请输入url" clearable></el-input>
        </el-form-item>

        <el-form-item label="用户信息存放位置">
          <el-radio v-model="setting.authInfoField" label="header">HEADER</el-radio>
          <el-radio v-model="setting.authInfoField" label="params">PARAMS</el-radio>
          <el-radio v-model="setting.authInfoField" label="body">BODY</el-radio>
        </el-form-item>

        <el-form-item label="自定义请求头">
          <br/>
          <div>
            <ol>
              <li v-for="(val,key) in this.setting.customHeaders">
                Key={{ key }} , Value={{ val }}
              </li>
            </ol>
          </div>
          <el-row>
            <el-col :span="7">
              <el-input v-model="customHeader_key" placeholder="Key" clearable></el-input>
            </el-col>
            <el-col :span="2">：</el-col>
            <el-col :span="7">
              <el-input v-model="customHeader_value" placeholder="Value" clearable></el-input>
            </el-col>

            <el-col :span="5">
              <el-button type="info" round @click="add_customer_header()">添加</el-button>
            </el-col>

          </el-row>
        </el-form-item>

        <el-form-item label="自定义请求参数">
          <br/>
          <div>
            <ol>
              <li v-for="(val,key) in this.setting.customParams">
                Key={{ key }} , Value={{ val }}
              </li>
            </ol>
          </div>
          <el-row>
            <el-col :span="7">
              <el-input v-model="customParams_key" placeholder="Key" clearable></el-input>
            </el-col>
            <el-col :span="2">：</el-col>
            <el-col :span="7">
              <el-input v-model="customParams_value" placeholder="Value" clearable></el-input>
            </el-col>

            <el-col :span="5">
              <el-button type="info" round @click="add_customer_params()">添加</el-button>
            </el-col>

          </el-row>
        </el-form-item>

        <el-form-item label="自定义请求体，会转换为json格式, 不支持嵌套">
          <br/>
          <div>
            <ol>
              <li v-for="(val,key) in this.setting.customBody">
                Key={{ key }} , Value={{ val }}
              </li>
            </ol>
          </div>
          <el-row>
            <el-col :span="7">
              <el-input v-model="customBody_key" placeholder="Key" clearable></el-input>
            </el-col>
            <el-col :span="2">：</el-col>
            <el-col :span="7">
              <el-input v-model="customBody_value" placeholder="Value" clearable></el-input>
            </el-col>

            <el-col :span="5">
              <el-button type="info" round @click="add_customer_body()">添加</el-button>
            </el-col>

          </el-row>

        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="setting_func()">设置</el-button>
        </el-form-item>

      </el-form>
    </div>
    <div>
      <div>
        <el-dialog
          title="设置结果"
          :visible.sync="dialogVisible"
          width="30%"
        >
          <p class="json_class">
            <json-view :data="setting_result"/>
          </p>
          <p>在用户登录时，用户信息会被放在：{{ this.$common.authInfoField }} 中</p>
          <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
        </span>
        </el-dialog>
      </div>
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
      setting: {
        authSchema: 'basic',
        httpMethod: 'get',
        loginUrl: '',
        authInfoField: 'header',
        customHeaders: {},
        customParams: {},
        customBody: {},
        forBearerTokenEnum:'',
        bearerTokenIssueUrl:''
      },
      dialogVisible: false,
      setting_result: "",
      customHeader_key: "",
      customHeader_value: "",
      customParams_key: "",
      customParams_value: "",
      customBody_key: "",
      customBody_value: "",
      showForBearerTokenEnum:false
    }
  },
  methods: {
    setting_func() {
      console.log("设置属性")
      // 将 authInfoField 设置为全局，方便客户登录使用
      this.$common.authInfoField = this.setting.authInfoField
      console.log(this.$common.authInfoField)
      let formData = JSON.stringify(this.setting)
      console.log(formData)
      console.log(this.setting.authSchema)
      axios({
        method: "post",
        url: "/set",
        headers: {
          'Content-type': 'application/json;charset=UTF-8'
        },
        data: formData
      }).then((res) => {

        this.setting_result = res.data
        this.setting_result.data = JSON.parse(res.data.data)
        this.dialogVisible = true
      })
    },
    add_customer_header() {
      this.$set(this.setting.customHeaders, this.customHeader_key, this.customHeader_value)
      this.customHeader_key = ""
      this.customHeader_value = ""
    },
    add_customer_params() {
      this.$set(this.setting.customParams, this.customParams_key, this.customParams_value)
      this.customParams_key = ""
      this.customParams_value = ""
    },
    add_customer_body() {
      this.$set(this.setting.customBody, this.customBody_key, this.customBody_value)
      this.customBody_key = ""
      this.customBody_value = ""
    },
    chooseAuthSchema(str){
      console.log(str)
      if(str==='bearer'){
        this.showForBearerTokenEnum=true
      }else{
        this.showForBearerTokenEnum=false
      }
    }
  }

}
</script>
<style>
.json_class{
  text-align: left;
}
</style>
