#!/bin/sh

rm -f tpid

nohup java -jar webhook-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev >webhook.log &

tpid=$!

echo 'Start Process...'    $tpid

echo $tpid > tpid

echo Start Success!
