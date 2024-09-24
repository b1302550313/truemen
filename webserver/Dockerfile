FROM openjdk:17
LABEL authors="Ethan"
# 设置工作目录
WORKDIR /app

# 将打包好的 JAR 文件复制到工作目录
COPY target/verto-0.0.1-SNAPSHOT.jar app.jar

# 暴露应用程序的端口
EXPOSE 8000

# 运行 JAR 文件
ENTRYPOINT ["java", "-jar", "app.jar"]
