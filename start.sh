#!/bin/bash

cd /home/ec2-user/GodPro_SERVER

git pull origin main

./gradlew build

sudo systemctl stop GodPro_SERVER.service

sudo systemctl start GodPro_SERVER.service