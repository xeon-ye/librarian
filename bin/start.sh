#!/bin/bash
nohup java \
-Xms4g \
-Xmx8g \
-XX:MetaspaceSize=256m \
-XX:MaxMetaspaceSize=512m \
-jar data-governance-system-2.0.2.0.jar >dgs.out 2>&1 &
PID=$(ps -ef |grep data-governance-system-2.0.2.0.jar |grep -v grep |awk '{print $2}')
if [ -z "PID" ]
then
    echo Data governance system startup failed!
else
    echo PID is $PID
    echo Data governance system is already started!
fi
