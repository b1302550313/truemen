# truemen

#### 1. 设置环境变量

将.env.example复制一份，重命名为.env，设置好每一个环境变量，如果缺少相应的环境变量，代码将无法运行。

#### 2. 利用Maven打包并启动Docker Compose
使用Maven将项目打包成一个可执行的JAR文件，使用Docker Compose启动所有相关的服务
```
. .env
mvn clean install
docker-compose up -d
```