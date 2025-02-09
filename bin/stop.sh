#!/bin/bash

JAR_NAME=app-demo.jar


ps aux | grep "$JAR_NAME" | grep -v grep | awk '{print $2}' | xargs kill -9
echo "stop application success!\n"