#!/bin/sh
APP_NAME=webhook-0.0.1-SNAPSHOT.jar

tpid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
if [ ${tpid} ]; then
    echo 'Stop Process...'   $tpid
    kill -15 $tpid
fi
sleep 5
tpid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
if [ ${tpid} ]; then
    echo 'Kill Process!'    $tpid
    kill -9 $tpid
else
    echo 'Stop Success!'
fi
