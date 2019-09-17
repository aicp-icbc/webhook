#!/bin/sh

rm -f tpid

nohup java -jar webhook-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev >wky_webhook.log &

tpid=$!

echo 'Start Process...'    $tpid

echo $tpid > tpid

echo Start Fished!

tail -200f wky_webhook.log