#!/bin/bash

JAR_NAME=app-demo.jar

chmod +x ${JAR_NAME}
ulimit -c unlimited
ulimit -n 102400

if [ -z "$APP_JAVA_JVM_OPTIONS" ] ; then
  APP_JAVA_JVM_OPTIONS="-Xms400m -Xmx400m"
fi

nohup java -server $APP_JAVA_JVM_OPTIONS -XX:+HeapDumpOnOutOfMemoryError -jar ${JAR_NAME} > /dev/null 2>&1&
