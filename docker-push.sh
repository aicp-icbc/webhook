#!/usr/bin/env bash

# 使用前请保证能够实现ssh免密登录和已经docker已经登录远程仓库
echo "使用前请保证能够实现ssh免密登录和已经docker已经登录远程仓库"

echo "开始打包docker镜像"

docker image ls

docker build -f ../Dockerfile -t ccr.ccs.tencentyun.com/ky229/icbc:1.9 .

echo "docker镜像打包完成"

docker image ls

#第一次需要登录
#docker login --username=100009218473 ccr.ccs.tencentyun.com

docker push ccr.ccs.tencentyun.com/ky229/icbc:1.9

echo "docker镜像推送完成，开始登录远程服务器"

ssh root@180.76.166.30

#没有使用免密登录需要输入密码
#set timeout 1
#ssh root@180.76.166.30
#expect "*password:"
#set password "password123"
#send "$password\n"
#interact



