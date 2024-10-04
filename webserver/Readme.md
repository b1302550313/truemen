# 后端启动环境配置

## 部署到本机

### 运行数据库

运行`init3.sql`文件

以Navicat为例，在Navicat中新建一个连接，选择MySQL，输入用户名和密码，连接到本地数据库，然后选择一个数据库，右键选择`运行SQL文件`，选择`init3.sql`文件，点击`确定`即可在本机上运行数据库。

如果报错，可尝试新建一个空白数据库，执行上述操作。

### 更改配置

在`webserver\src\main\resources\application.properties`中更改如下配置

```bash
# 将verto改为你的数据库名
spring.datasource.url=jdbc:mysql://localhost:3306/verto?useSSL=false&serverTimezone=UTC

# username一般为root,无需更改
spring.datasource.username=root

# 改为自己的数据库密码
spring.datasource.password=Lwy20030403!


# 微信配置：改为你自己的微信号和微信密码
wechat.appid=your_app_id
wechat.secret=your_secret_key
```

### 安装依赖

首先进入`webserver`目录
```bash
cd webserver
```

### 安装OpenJDK 17
在使用Maven打包之前，需要安装OpenJDK 17
```bash
sudo apt update
sudo apt install openjdk-17-jdk
```

执行
```bash
mvn clean install
```


### 启动

在`webserver`目录执行
```bash
mvn spring-boot:run
```

## 部署到服务器

待补充




