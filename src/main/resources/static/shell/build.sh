#!/bin/bash
# $1 UUID
# $2 仓库地址
# $3 部署路径
# $4 Git分支

if [ -z "$1" ]||[ -z "$3" ]; then
    echo "参数不能为空"
    exit 0
fi

# 创建目录
mkdir -p $3/$1
cd $3/$1

if [ -d "$3/$1/src" ];then
    cd src
    git pull
else
    git clone $2 src
    cd src
fi

# 克隆
if [ "$4" != "null" ]; then
    git checkout $4
fi

mvn clean package -Dmaven.test.skip=true

cd ..
if [ ! -d "$3/$1/pkg" ];then
    mkdir pkg
fi

mv src/target/*.jar pkg
