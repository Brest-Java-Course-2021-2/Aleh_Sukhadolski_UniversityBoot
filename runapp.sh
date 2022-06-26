#!/bin/bash

# Buid the package
mvn clean install

#Create static network oleg-university
sudo docker network create --subnet=192.168.5.7/16 oleg_university

cd rest-application
docker build -t university-rest .
cd ..
cd web-application
docker build -t university-web .
cd ..
docker run --net oleg_university --ip 192.168.5.10 -d -p 8898:8898 --name university-rest  university-rest
docker run --net oleg_university --ip 192.168.5.11 -d -p 8899:8899 --name university-web  university-web