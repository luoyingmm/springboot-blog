## 简介

最近玩了玩Spring Boot，学写了一个简单的博客后台，采用了Spring Boot + Vue 的前后端分离系统。其中blog-admin是一个基于security后台权限管理系统（随便写着玩的），blog-api是博客后台，blog-app是前端Vue项目。

## 后端技术使用

* 核心框架：Spring Boot
* 数据库：mysql
* 持久层框架：MyBatis-Plus
* 登录Token认证：jwt
* 缓存管理：Redis
* 图床：七牛云

## 功能

文章列表、最热标签、最热文章、最新文章、文章归档、文章分类。登录注册账号即可在线采用Markdown格式编写文章，可以插入图片。

## 项目运行

前端(blog-app)：
- npm install（下载依赖）
- npm run build (打包)
- npm run dev (运行)

后端(blog-api)：
* 先创建一个mysql数据库，之后运行blog.sql文件
* 修改application.properties数据库配置
* 启动Redis
* 运行项目

## 关于

* 本项目借鉴于[**码神之路**](https://space.bilibili.com/473844125)的视频完成
* 这是作者第一个Spring Boot项目，存在很多瑕疵，欢迎提出问题。
* 邮箱：[1502972236zwj@gmail.com](mailto:1502972236zwj@gmail.com)，欢迎与我联系。

