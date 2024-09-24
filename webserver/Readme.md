# 后端启动环境配置

## 启动程序

在IDE中运行`webserver\src\main\java\com\sysu\verto\VertoApplication.java`中main方法

![image-20240807182213014](http://typoraimagess.oss-cn-beijing.aliyuncs.com/img/image-20240807182213014.png)

控制台输出如下表示运行成功：

![image-20240807182248537](http://typoraimagess.oss-cn-beijing.aliyuncs.com/img/image-20240807182248537.png)

**注意：如果修改了代码，再次调试之前记得重新run一遍**

## 使用curl测试文件存储API

以下是使用`curl`测试文件存储API的详细说明和示例命令。在命令行中运行这些命令。

### 文件上传接口 `upload`

使用以下命令上传文件。确保路径使用正斜杠（`/`）。

```sh
curl -F "file=@<path_to_file>" http://localhost:8000/files/upload/<userId>/<module>
```

**示例**：

```sh
curl -F "file=@C:/Users/HP/Desktop/temp/test1.png" http://localhost:8000/files/upload/123/avatar
```

解释：
- `"file=@/path/to/your/file"`：待上传的文件路径名，建议使用正斜杠（`/`）。
- `localhost:8000`：服务器地址和端口，可以在`webserver/src/main/resources/application.properties`中修改端口。
- 如果路径报错，可尝试将`application.properties`中的`file.base-dir`改为绝对路径。

### 文件下载接口 `download`

使用以下命令下载文件到当前目录，并使用服务器提供的文件名保存。

```sh
curl -o <output_file_path> http://localhost:8000/files/download/<userId>/<module>/<fileName>
```

**示例**：

```sh
curl -o downloaded_test1.png http://localhost:8000/files/download/123/avatar/test1.png
```

解释：
- `-o <output_file_path>`：指定下载文件的输出路径和文件名。
- `localhost:8000`：服务器地址和端口。
- `<userId>`和`<module>`：指定用户ID和功能模块。

### 文件删除接口 `delete`

使用以下命令删除文件。

```sh
curl -X DELETE http://localhost:8000/files/delete/<userId>/<module>/<fileName>
```

**示例**：

```sh
curl -X DELETE http://localhost:8000/files/delete/123/avatar/test1.png
```

解释：
- `-X DELETE`：指定HTTP DELETE请求方法。
- `localhost:8000`：服务器地址和端口。
- `<userId>`和`<module>`：指定用户ID和功能模块。

### 列出文件接口 `list`

使用以下命令列出指定用户和模块下的所有文件。

```sh
curl http://localhost:8000/files/list/<userId>/<module>
```

**示例**：

```sh
curl http://localhost:8000/files/list/123/avatar
```

解释：
- `localhost:8000`：服务器地址和端口。
- `<userId>`和`<module>`：指定用户ID和功能模块。

### 注意事项
- 确保服务器正在运行，并且配置正确。
- 根据需要在`webserver/src/main/resources/application.properties`中调整端口和路径配置。
- 如果路径报错，使用绝对路径可能会更可靠。

