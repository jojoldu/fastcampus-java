#!/bin/bash
REPOSITORY_DIR=/home/git/jwp-basic
PID="ps -ef|grep tomcat|grep java| awk ' { print $2 } '"
cd $REPOSITORY_DIR
sudo git pull
sudo mvn clean package -Dmaven.test.skip=true
kill -9 $PID
mv $REPOSITORY_DIR/target/jwp-basic /usr/local/tomcat8/webapps/ROOT
startup
