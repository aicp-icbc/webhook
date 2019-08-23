#指定以openjdk:8-jre 为基础镜像，来构建此镜像，可以理解为运行的需要基础环境
FROM openjdk:8-jre

#签名信息
MAINTAINER wukaiyun <kaiyun.wu@qq.com>

LABEL Description="WeebHook For AICP - ICBC"  Version="latest"

#共享数据
VOLUME /log

#WORKDIR指令用于指定容器的一个目录， 容器启动时执行的命令会在该目录下执行。
WORKDIR /dock

#将jar包 添加为webhook.jar
ADD webhook-0.0.1-SNAPSHOT.jar webhook.jar

#暴露容器端口为8899 Docker镜像告知Docker宿主机应用监听了8899端口
EXPOSE 8899

#容器启动时执行的命令
#CMD java -jar webhook.jar

#容器启动后，最终执行的命令
ENTRYPOINT java -jar webhook.jar