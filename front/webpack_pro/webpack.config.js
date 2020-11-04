/**
 * 以下配置的意思是：
 * 读取当前项目目录下src文件夹中的main.js（入口文件）内容，分析资源依赖，把相关的js文件打包
 * 打包后的文件放入当前目录的dist文件夹下
 * 打包后的js文件名为bundle.js
 */

const path = require("path") //Node.js内置模块
module.exports = {
    entry: './src/main.js', //配置入口文件
    output: {
        path: path.resolve(__dirname, './dist'), //输出路径，__dirname：当前文件所在路径
        filename: 'bundle.js' //输出文件
    },
    module: {
        rules: [ {
            test: /\.css$/,    //打包规则应用到以css结尾的文件上
            use: ['style-loader', 'css-loader']
        } ]
    }
}

//webpack --mode=development
// 修改package.json文件
//npm run dev #开发打包
//或
//npm run prod #生产打包