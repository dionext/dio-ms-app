#!/bin/bash

scp target/*.jar root@$REMOTE_SERVER_ADDRESS:/mnt/$APP_NAME/app/target/java-app.jar
scp Dockerfile root@$REMOTE_SERVER_ADDRESS:/mnt/$APP_NAME/app/Dockerfile

ssh -t root@$REMOTE_SERVER_ADDRESS << EOF
cd /mnt/$APP_NAME
docker-compose build
. ./redeploy-app.sh && zero_downtime_deploy
EOF



