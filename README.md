### 1.Project environment
| tools | vision |
| :------:| :------: |
| IDEA | 2018.3 |
| SpringBoot | 2.1.3 RELEASE |
| Gradle|5.2.1+|
| JDK|1.8|
| Spring Cloud|Greenwich.RELEASE|

#### 2.Start

Please edit hosts 

```
sudo vim /etc/hosts
```
add 

```
127.0.0.1 tools
```
At last,:wq save this file.

### 项目结构

```
├─tools----------------------------父项目/公共依赖管理
│  │
│  ├─tools-eureka---------------------------注册中心[8761,8762]
│  │
│  ├─tools-config---------------------------配置中心[7001,7002]
│  │
│  ├─tools-gateway--------------------------网关中心[9999]
│  │
│  ├─tools-auth-----------------------------认证授权中心[3000]
│  │
│  ├─tools-admin----------------------------权限管理系统业务处理模块[4000]
│  │
│  ├─tools-common
│  │  │
│  │  ├─tools-common-api--------------------通用用户权限管理系统公共api模块
│  │  │
│  │  ├─tools-common-core-------------------公共工具类核心包
│  │  │
│  │  ├─tools-common-log--------------------日志服务
│  │  │
│  │  └─tools-common-security---------------安全工具类
└─ └─
```

### 3.Project Building

###### 3.1 Gradle clean

> Appear as follows

```shell
cd [current project path]
gradle clean
```

result:  

```
# cuifuan @ cuifuandeMacBook-Pro in ~/workspaces/tools on git:master o [11:30:14]
$ gradle clean

Deprecated Gradle features were used in this build, making it incompatible with Gradle 6.0.
Use '--warning-mode all' to show the individual deprecation warnings.
See https://docs.gradle.org/5.2.1/userguide/command_line_interface.html#sec:command_line_warnings

BUILD SUCCESSFUL in 0s
6 actionable tasks: 6 executed
```

###### 3.2 Gradle dockerSyncBuildContext

```shell
# cuifuan @ cuifuandeMacBook-Pro in ~/workspaces/tools on git:master x [11:35:18]
$ gradle dockerSyncBuildContext

Deprecated Gradle features were used in this build, making it incompatible with Gradle 6.0.
Use '--warning-mode all' to show the individual deprecation warnings.
See https://docs.gradle.org/5.2.1/userguide/command_line_interface.html#sec:command_line_warnings

BUILD SUCCESSFUL in 9s
12 actionable tasks: 12 executed
```

###### 3.3 Gradle dockerSyncBuildContext

```shell
# cuifuan @ cuifuandeMacBook-Pro in ~/workspaces/tools on git:master x [11:36:47]
$ gradle dockerBuildImage

> Task :tools-crud-jpa:dockerBuildImage
Building image using context '/Users/cuifuan/workspaces/tools/tools-crud-jpa/build/docker'.
Using tags 'store.zabbix/tools-crud-jpa:1.0' for image.

Step 1/7 : FROM openjdk:8-alpine

---> e9ea51023687
Step 2/7 : WORKDIR /app

---> Using cache
---> 1cd5699c22ec
Step 3/7 : COPY libs libs/

---> 6623e5879cf6
Step 4/7 : COPY resources resources/

---> 7c5ff16fee64
Step 5/7 : COPY classes classes/

---> 573f57a1c221
Step 6/7 : ENTRYPOINT ["java", "-cp", "/app/resources:/app/classes:/app/libs/*", "store.zabbix.toolscrudjpa.ToolsJpaExtraApplication"]

---> Running in b23dba86abc9
Removing intermediate container b23dba86abc9
---> 599175ec7eb0
Step 7/7 : EXPOSE 8769 8769

---> Running in a36ceb32465c
Removing intermediate container a36ceb32465c
---> 7c88767b64f9
Successfully built 7c88767b64f9
Successfully tagged store.zabbix/tools-crud-jpa:1.0

> Task :tools-crud-jpa:dockerBuildImage
Created image with ID '7c88767b64f9'.

> Task :tools-eureka-server:dockerBuildImage
Building image using context '/Users/cuifuan/workspaces/tools/tools-eureka-server/build/docker'.
Using tags 'store.zabbix/tools-eureka-server:1.0' for image.

Step 1/7 : FROM openjdk:8-alpine

---> e9ea51023687
Step 2/7 : WORKDIR /app

---> Using cache
---> 1cd5699c22ec
Step 3/7 : COPY libs libs/

---> Using cache
---> 405f3a41d1f4
Step 4/7 : COPY resources resources/

---> Using cache
---> 05a270446381
Step 5/7 : COPY classes classes/

---> Using cache
---> f14f221a9286
Step 6/7 : ENTRYPOINT ["java", "-cp", "/app/resources:/app/classes:/app/libs/*", "store.zabbix.eureka.ToolsEurekaApplication"]

---> Using cache
---> 20cb31db5b91
Step 7/7 : EXPOSE 8761 8761

---> Using cache
---> e3d954cac097
Successfully built e3d954cac097
Successfully tagged store.zabbix/tools-eureka-server:1.0

> Task :tools-eureka-server:dockerBuildImage
Created image with ID 'e3d954cac097'.

Deprecated Gradle features were used in this build, making it incompatible with Gradle 6.0.
Use '--warning-mode all' to show the individual deprecation warnings.
See https://docs.gradle.org/5.2.1/userguide/command_line_interface.html#sec:command_line_warnings

BUILD SUCCESSFUL in 12s
16 actionable tasks: 5 executed, 11 up-to-date
```

### 4.Docker

###### 4.1 View Docker images
```shell
# cuifuan @ cuifuandeMacBook-Pro in ~/workspaces/tools on git:master x [11:37:46]
$ docker images
REPOSITORY                         TAG                 IMAGE ID            CREATED              SIZE
store.zabbix/tools-crud-jpa        1.0                 7c88767b64f9        About a minute ago   167MB
store.zabbix/tools-eureka-server   1.0                 e3d954cac097        19 hours ago         159MB
<none>                             <none>              8fb0734097be        19 hours ago         165MB
openjdk                            8-alpine            e9ea51023687        3 weeks ago          105MB
```


###### 4.2 Run Docker images

> -d make docker images in the background

###### 4.3 Run eureka-server
> docker run -d --network tools -p 8761:8761 store.zabbix/tools-eureka-server:1.0
```shell
# cuifuan @ cuifuandeMacBook-Pro in ~/workspaces/tools on git:master x [11:39:02]
$ docker run -d --network tools -p 8761:8761 store.zabbix/tools-eureka-server:1.0
41ca14f89cd81bf4560254647d1763db7b6853b166c74f359bd8d794e5e48f9c
```

###### 4.4 Run eureka client

> docker run -d --network tools -p 8769:8769 store.zabbix/tools-crud-jpa:1.0

```shell
# cuifuan @ cuifuandeMacBook-Pro in ~/workspaces/tools on git:master x [11:43:03]
$ docker run -d --network tools -p 8769:8769 store.zabbix/tools-crud-jpa:1.0
aa845590b74adfa8b725b0eb44020f3b02a1520ec9e802d6cc5bab7e40e0d6dd
```

##### 4.5 Access URL 
> http://10.0.0.82:8761/  

![](http://ww1.sinaimg.cn/large/d1db9fd3ly1g1jis5yeavj22ym1jg7g4.jpg)

> http://10.0.0.82:8769/actuator/info  

![](http://ww1.sinaimg.cn/large/d1db9fd3ly1g1jitol1t7j22y61e2wlu.jpg)
