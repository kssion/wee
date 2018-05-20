#!/bin/bash
# $1 UUID
# $2 仓库地址
# $3 Git分支
# $4 项目部署路径

if [ -z "$1" ]||[ -z "$4" ]; then
    echo "参数不能为空"
    exit 0
fi

# 创建目录
mkdir -p $4/$1
cd $4/$1

# 克隆
git clone $2 .
if [ "$3" != "null" ]; then
    git checkout $3
fi

mvn clean package -Dmaven.test.skip=true
