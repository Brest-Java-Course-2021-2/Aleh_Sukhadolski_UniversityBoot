#!/bin/bash

# Buid the package

#docker pull wurstmeister/zookeeper
#docker pull wurstmeister/kafka
#docker run -d --name zookeeper -p 2181:2181 -t wurstmeister/zookeeper
#docker run  -d --name kafka -p 9092:9092 -e KAFKA_BROKER_ID=0 -e KAFKA_ZOOKEEPER_CONNECT=0.0.0.0:2181 -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://0.0.0.0:9092 -e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092 -t wurstmeister/kafka


mvn clean install
#Create static network oleg-university
sudo docker network create --subnet=192.168.5.7/16 oleg_university

cd rest-application
sudo docker build -t university-rest .
cd ..
cd web-application
sudo docker build -t university-web .
cd ..
sudo docker run --net oleg_university --ip 192.168.5.10 -d -p 8898:8898 --name university-rest  university-rest
sudo docker run --net oleg_university --ip 192.168.5.11 -d -p 8899:8899 --name university-web  university-web

# Clone and run project
# git clone https://github.com/Brest-Java-Course-2021-2/Aleh_Sukhadolski_UniversityBoot -b kafka-webapp
# cd Aleh_Sukhadolski_UniversityBoot
# sh runapp.sh
# endpoint in the browser
# 192.168.5.11:8899/lectors
# CLICK to CREATE SCHEDULE

# after when you enjoy this magnificient job run
# sh stopapp.sh