module.exports = {
    devServer: {
      port: 80,
      proxy: {
        // 配置跨域
        '/set':{
          target: 'http://localhost:8081/api',
          ws: true,
          changOrigin: true
        },
        '/login':{
          target: 'http://localhost:8081/api',
          ws:true,
          changOrigin: true
        }
      }
    },
  }
