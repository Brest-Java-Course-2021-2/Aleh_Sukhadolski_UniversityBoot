#DOCKER BUILD :    docker build -t web-application:0.0.1 .
# DOCKER RUN  :      docker run -d -p 8899:8899 -t web-application:0.0.1

FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=target/web-application-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} webapp.jar
ENTRYPOINT ["java","-jar","webapp.jar"]