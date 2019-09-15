#!/usr/bin/env bash
echo "dowing"
docker pull ccr.ccs.tencentyun.com/ky229/icbc:1.9
docker stop icbc
docker rm icbc
docker run -d --name icbc -p 8899:8899 ccr.ccs.tencentyun.com/ky229/icbc:1.9
docker logs -f icbc