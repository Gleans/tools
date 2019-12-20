# 基于哪个镜像
#FROM openjdk:8u191-jdk-alpine3.9
#
## 将本地文件夹挂载到当前容器
#VOLUME /tmp
## 创建人
#
#
#ARG JAR_FILE
#ADD ${JAR_FILE} /tools/app.jar
### 配置容器启动后执行的命令
#ENTRYPOINT  ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/tools/app.jar"]
FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER eyck.cui <eyck.cui@foxmail.com>

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /tools-uaa/target

WORKDIR /tools-uaa

EXPOSE 8001

ADD ./tools-uaa/target/tools-uaa.jar ./

CMD java -Xdebug -Djava.security.egd=file:/dev/./urandom -jar tools-uaa.jar