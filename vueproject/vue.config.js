const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer:{
    port: 7000 //前端端口号
  },
  chainWebpack:config => {
    config.plugin('html')
        .tap(args => {
        args[0].title = "我是标题";  //标题名称
        return args;
    })
  }
})
