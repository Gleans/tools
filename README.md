# Spring Cloud 项目脚手架

## 版本说明

| 工具 | 版本 |
| ---- | ---- |
| JDK| 1.8 |
| spring.cloud.version | Hoxton.SR8 |
| spring.boot.version | 2.3.2.RELEASE |
| spring.cloud.alibaba.version | 2.2.5.RELEASE |
| Nacos Version | 2.0.1 |

## 模块注释  

| 模块 | 功能 |备注 |
| ---- | ---- |---- |
| pro-common  | 公共模块 |公共实体、工具类等|
| pro-auth  | 鉴权模块 |授权服务和资源服务|
| pro-gateway  | 网关模块 |路由转发|
| pro-view  | 前端模块 |前后端分离|

MySQL文件：[cloud_pro.sql](./pro-auth/src/main/resources/sql/cloud_pro.sql)
## 安装 Nacos 

下载地址：  
[https://github.com/alibaba/nacos/releases/download/2.0.1/nacos-server-2.0.1.zip](https://github.com/alibaba/nacos/releases/download/2.0.1/nacos-server-2.0.1.zip)

本机是windows环境:  
- 下载完成后解压 
- 解压后`nacos`文件夹放到你喜欢的位置  
- 在命令行定位到`nacos/bin`的位置（例如 d:;cd nacos\bin;）

### nacos持久化  

1. 新建数据库名为 `nacos_config`  
2. 导入`nacos/conf` 下的`nacos_mysql.sql` 文件  
3. 使用`IDE`工具修改 `nacos/conf` 下面的`application.properties`文件  

找到`Connect URL of DB`修改,下面是我的示例，根据你的数据库配置自行修改：  

```properties
### Count of DB:
db.num=1

### Connect URL of DB:
db.url.0=jdbc:mysql://127.0.0.1:3306/nacos_config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC
db.user.0=root
db.password.0=root
```
### 启动 Nacos  

windows启动命令如下：  
```shell
D:\nacos\bin> .\startup.cmd -m standalone
```