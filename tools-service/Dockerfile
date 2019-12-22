FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER eyck.cui <eyck.cui@foxmail.com>

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /tools-service/target

WORKDIR /tools-service

EXPOSE 8848

ADD ./tools-service/target/tools-service.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar tools-service.jar