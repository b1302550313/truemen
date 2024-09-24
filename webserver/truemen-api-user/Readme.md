## 启动User模块

1. 在`webserver\truemen-api-user\src\main\resources\application.properties`中修改配置
```
# 数据库名称
spring.datasource.url=jdbc:mysql://localhost:3306/verto?useSSL=false&serverTimezone=UTC
# 数据库用户名（一般都是root不用改）
spring.datasource.username=root
# 数据库密码
spring.datasource.password=your_password_of_mysql

# 微信配置(微信号和微信登录密码，改成自己的进行测试)
wechat.appid=your_wechat_appid  
wechat.secret=your_wechat_secret
```


   
2. 启动User模块
进入`webserver\truemen-api-user`，执行以下命令：
```
mvn spring-boot:run
```