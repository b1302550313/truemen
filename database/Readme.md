## 数据库框架

### 待解决的问题

- [ ] 含音频、视频、图像的帖子内容如何储存
- [ ] **2024-9-26** 已经将`init.sql` 中的建表SQL和数据SQL拆分，并可以通过脚本`build_database.bat` 一键完成数据库初始化，但还需对`init2.sql` 、`init3.sql` 同样处理

### 数据库搭建方法

- 依次运行`init1.sql`、`init2.sql`、`init3.sql`文件

  以Navicat为例，在Navicat中新建一个连接，选择MySQL，输入用户名和密码，连接到本地数据库，然后选择一个数据库，右键选择`运行SQL文件`，依次选择`init1.sql`、`init2.sql`、`init3.sql`文件，点击`确定`即可在本机上运行数据库。

  如果报错，可尝试新建一个空白数据库，执行上述操作。

- 修改`config.txt`中的信息，运行目录中的`build_database.bat` ==（未完成，切勿运行，否则会导致数据库发生数据缺失）==

  **由于Windows命令行默认编码为GBK，所以该脚本以GBK编码进行保存，如果打开发现中文乱码，请切换编码为GBK打开！**

### 数据库更改记录

- past

  - user表分为user_base_info表和user_core_info表【注意统一】

  - 添加了部分数据

  - 修改了性别，添加了匿。新的数据库文件是init2.sql。注意执行init2.sql。
  - 增加了zodiac星座字段。注意执行最新的init3.sql

