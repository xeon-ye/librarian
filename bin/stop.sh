#!/bin/bash
PID=$(ps -ef |grep data-governance-system-2.0.2.0.jar |grep -v grep |awk '{print $2}')
if [ -z "PID" ]
then
    echo Spring Boot Application is already stopped!
else
    echo kill $PID
    kill $PID
    echo Spring Boot Application is already stopped!
fi

