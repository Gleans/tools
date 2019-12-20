FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER eyck.cui <eyck.cui@foxmail.com>

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /tools-gateway/target

WORKDIR /tools-gateway

EXPOSE 8443

ADD ./tools-gateway/target/tools-gateway.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar tools-gateway.jar