# 基础镜像
FROM frolvlad/alpine-oraclejdk8:slim

# 让容器可以访问宿主机上的目录。
# 在主机/var/lib/docker目录下创建了一个临时文件，并链接到容器的/tmp。
# /tmp目录用来持久化到Docker数据文件夹，因为 Spring Boot 使用的内嵌 Tomcat 容器默认使用/tmp作为工作目录
VOLUME /tmp

# 从源系统上复制文件到目标容器的文件系统。
# 将项目的jar文件复制到到容器中，作为 "app.jar"。
# 注意里面的ADD xxx.jar是你使用maven打包之后的jar包的名称。
ADD tools-crud-jpa-1.0.jar tools-crud-jpa.jar
#ARG JAR_FILE
#ADD ${JAR_FILE} app.jar

# RUN表示在新创建的镜像中执行一些命令，然后把执行的结果提交到当前镜像。
# 这里使用touch命令来改变文件的修改时间，Docker创建的所有容器文件默认状态都是“未修改”。
# 这对于简单应用来说不需要，不过对于一些静态内容（比如：index.html）的文件就需要一个“修改时间”。
#RUN sh -c 'touch /app.jar'


#内存设置
#ENV JAVA_OPTS="-server -Xms2048m -Xmx2048m -Xss512k"

# 容器运行后默认执行的命令：用于执行项目app.jar。
# 为了缩短Tomcat的启动时间，添加一个系统属性指向 "/dev/urandom" 作为EntropySource。
ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar /tools-crud-jpa.jar" ]
