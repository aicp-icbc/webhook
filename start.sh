#!/bin/sh

rm -f tpid

rm -f back-webhook-server.jar

mv webhook-server.jar  back-webhook-server.jar

mv webhook-0.0.1-SNAPSHOT.jar webhook-server.jar

nohup java -jar webhook-server.jar --spring.profiles.active=dev >webhook.log &

echo $! > tpid

echo Start Success!
