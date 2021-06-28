### 基于WebService的分布式图书信息管理系统

#### 开发模式
项目整体使用前后端分离的开发模式，此项目为后端项目
#### 前端项目
- [book_manager](https://github.com/NobitaNobi001/book_manager_front.git)


### 使用教程
#### 后端

1. IDEA安装lombok插件

2. MySQL（版本8.0.16），新建数据库导入book.sql

3. 修改数据库配置

4. 将项目内的局域网ip全局替换为自己所连接的网络ip

### 后端技术选型
- [Spring Boot 2.3.3](http://spring.io/projects/spring-boot/)
- [MyBatisPlus](https://mp.baomidou.com/guide/)
- [MySQL 8.0.16](https://dev.mysql.com/downloads/mysql/8.0.16.html#downloads)

### 注意事项
1. 此项目是局域网内的客户端可以从服务端的WebService上拉取图书信息同步到本地
2. 此项目需要配合前端项目使用，图形化界面效果更佳