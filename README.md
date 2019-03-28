### Project environment
| tools | vision |
| :------:| :------: |
| IDEA | 2018.3 |
| SpringBoot | 2.1.3 RELEASE |
| Gradle|5.2.1+|
| JDK|1.8|
| Spring Cloud|Greenwich.RELEASE|

#### Start

Please edit hosts 

```
sudo vim /etc/hosts
```
add 

```
127.0.0.1 springcloud-tools
```

At last,:wq save this file.


```
├─springcloud-tools----------------------------父项目，公共依赖
│  │
│  ├─tools-eureka-server---------------------------微服务注册中心[8761,8762]
│  │
│  ├─tools-config-server---------------------------微服务配置中心
│  │
│  ├─tools-monitor---------------------------------微服务监控中心
│  │
│  ├─tools-zipkin-server---------------------------微服务日志采集中心
│  │
│  ├─tools-gateway---------------------------------微服务网关中心
│  │
│  ├─tools-provider
│  │  │
│  │  ├─tools-provider-mdc------------------数据服务中心
│  │  │
│  │  ├─tools-provider-omc------------------订单服务中心
│  │  │
│  │  ├─tools-provider-opc------------------对接服务中心
│  │  │
│  │  ├─tools-provider-tpc------------------任务服务中心
│  │  │
│  │  └─tools-provider-uac------------------用户服务中心
│  │
│  ├─tools-provider-api
│  │  │
│  │  ├─tools-provider-mdc-api------------------数据服务中心API
│  │  │
│  │  ├─tools-provider-omc-api------------------订单服务中心API
│  │  │
│  │  ├─tools-provider-opc-api------------------对接服务中心API
│  │  │
│  │  ├─tools-provider-tpc-api------------------任务服务中心API
│  │  │
│  │  ├─tools-provider-sdk-api------------------可靠消息服务API
│  │  │
│  │  └─tools-provider-uac-api------------------用户服务中心API
│  │
│  ├─tools-common
│  │  │
│  │  ├─tools-common-base------------------公共POJO基础包
│  │  │
│  │  ├─tools-common-config------------------公共配置包
│  │  │
│  │  ├─tools-common-core------------------微服务核心依赖包
│  │  │
│  │  ├─tools-common-util------------------公共工具包
│  │  │
│  │  ├─tools-common-zk------------------zookeeper配置
│  │  │
│  │  ├─tools-security-app------------------公共无状态安全认证
│  │  │
│  │  ├─tools-security-core------------------安全服务核心包
│  │  │
│  │  └─tools-security-feign------------------基于auth2的feign配置
│  │
│  ├─tools-generator
│  │  │ 
│  │  ├─tools-generator-mdc------------------数据服务中心Mybatis Generator
│  │  │
│  │  ├─tools-generator-omc------------------数据服务中心Mybatis Generator
│  │  │
│  │  ├─tools-generator-opc------------------数据服务中心Mybatis Generator
│  │  │
│  │  ├─tools-generator-tpc------------------数据服务中心Mybatis Generator
│  │  │
│  │  └─tools-generator-uac------------------数据服务中心Mybatis Generator

```