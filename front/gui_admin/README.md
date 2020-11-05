# vue-admin-template

> A minimal vue admin template with Element UI & axios & iconfont & permission control & lint

**Live demo:** http://panjiachen.github.io/vue-admin-template

[中文文档](https://github.com/PanJiaChen/vue-admin-template/blob/master/README-zh.md)

## Build Setup

```bash
# Clone project
git clone https://github.com/PanJiaChen/vue-admin-template.git

# Install dependencies
npm install

# Serve with hot reload at localhost:9528
npm run dev

# Build for production with minification
npm run build

# Build for production and view the bundle analyzer report
npm run build --report
```

## Demo

![demo](https://github.com/PanJiaChen/PanJiaChen.github.io/blob/master/images/demo.gif)

## Extra

If you want router permission && generate menu by user roles , you can use this branch [permission-control](https://github.com/PanJiaChen/vue-admin-template/tree/permission-control)

This project is based on `webpack4` development. If you want to use `webpack3` development, please use this branch [webpack3](https://github.com/PanJiaChen/vue-admin-template/tree/webpack3)

For `typescript` version, you can use [vue-typescript-admin-template](https://github.com/Armour/vue-typescript-admin-template) (Credits: [@Armour](https://github.com/Armour))

## Related Project

[vue-element-admin](https://github.com/PanJiaChen/vue-element-admin)

[electron-vue-admin](https://github.com/PanJiaChen/electron-vue-admin)

[vue-typescript-admin-template](https://github.com/Armour/vue-typescript-admin-template)

### Element-Ui using cdn tutorial

First find `index.html`([root directory](https://github.com/PanJiaChen/vue-admin-template/blob/element-ui-cdn/index.html))

Import css and js of `Element`, and then import vue. Because `Element` is vue-dependent, vue must be import before it.

Then find [webpack.base.conf.js](https://github.com/PanJiaChen/vue-admin-template/blob/element-ui-cdn/build/webpack.base.conf.js)
Add `externals` to make webpack not package vue and element.

```
externals: {
  vue: 'Vue',
  'element-ui':'ELEMENT'
}
```

Finally there is a small detail to pay attention to that if you import vue in global, you don't need to manually `Vue.use(Vuex)`, it will be automatically mounted, see
[issue](https://github.com/vuejs/vuex/issues/731)

And you can use `npm run build --report` to see the effect

Pictured:
![demo](https://panjiachen.github.io/images/element-cdn.png)

**[Detailed code](https://github.com/PanJiaChen/vue-admin-template/commit/746aff560932704ae821f82f10b8b2a9681d5177)**

**[Branch](https://github.com/PanJiaChen/vue-admin-template/tree/element-ui-cdn)**

## Browsers support

Modern browsers and Internet Explorer 10+.

| [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/edge/edge_48x48.png" alt="IE / Edge" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>IE / Edge | [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/firefox/firefox_48x48.png" alt="Firefox" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Firefox | [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/chrome/chrome_48x48.png" alt="Chrome" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Chrome | [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/safari/safari_48x48.png" alt="Safari" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Safari |
| --------- | --------- | --------- | --------- |
| IE10, IE11, Edge| last 2 versions| last 2 versions| last 2 versions

## License

[MIT](https://github.com/PanJiaChen/vue-admin-template/blob/master/LICENSE) license.

Copyright (c) 2017-present PanJiaChen



start-build-setup
1 package.json  //npm配置
  scripts 
    --config build/webpack.dev.conf.js
2 webpack.dev.conf.js  //开发配置
  require('./webpack.base.conf')
3 webpack.base.conf.js //基础配置
  module.exports
    app: './src/main.js'  //webpack打包组合配置，配置入口文件
4 main.js
  new Vue //导入所有需要的js，创建vue实例
    rotuer //#锚点配置
5 webpack.base.conf.js
  module.exports
    output filename //组合后的js名称
6 webpack.dev.conf.js
    plugins
      new HtmlWebpackPlugin filename: 'index.html', //嵌入最后打包后的js所在的文件


#一、前端项目组件分析
1、三要素
入口js：src/main.js
入口页面：src/App.vue
路由：src/router/index.js
main.js 中引入了App.vue和 router/index.js，根据路由配置，App.vue中会显示相应的页面内容
2、入口文件
```javascript
import App from './App'
import router from './router'

new Vue({
  el: '#app',
  router,//挂载路由模块到vue实例中
  store,
  render: h => h(App)//使用app组件完成页面渲染
})
```
3、主页面模块
src/App.vue
```vue
<router-view/>
在此位置显示路由配置中指定的组件
```
4、路由模块
src/router/index.js
```javascript
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  //如果用户访问路由“/login”,则在App vue中显示“/views/login/index”中的内容
```

#三、前端项目布局分析
1、路由模块
src/router/index.js
```javascript
 // 讲师管理
  {
    path: '/teacher',
    component: Layout, //应用布局 Layout vue
    redirect: '/teacher/list',
    name: 'Teacher',
    meta: { title: '讲师管理' },
    children: [
      {
        path: 'list',
        name: 'TeacherList',
        //当用户访问路由“/teacher/list”的时候，在路由出口处加载“/views/teacher/list.vue”组件
        component: () => import('@/views/teacher/list'), 
        
        meta: { title: '讲师列表' }
      },
```
2、布局模块
src/views/layout/Layout.vue
```vue
//整个页面布局由三部分组成
<sidebar class="sidebar-container"/>  //顶部边栏
    <div class="main-container">  
      <navbar/>   //侧边栏
      <app-main/> //主内容区
    </div>
```
3、核心内容区域
src/views/layout/components/AppMain.vue
```vue
//路由出口，根据路由显示对应的页面组件
<router-view :key="key"/>
```