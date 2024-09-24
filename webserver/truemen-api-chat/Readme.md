## 启动chat模块

1. 在`webserver\truemen-api-chat\src\main\resources\application.yml`中修改配置
```
# 数据库名称：将url中verto替换成自己的数据库名称

url: jdbc:mysql://localhost:3306/verto?useUnicode=true&characterEncoding=utf8

# 数据库用户名及密码
  datasource:
    username: root
    password: your_password_of_mysql
```


   
2. 启动chat模块
进入`webserver\truemen-api-chat`，执行以下命令：
```
mvn spring-boot:run
```