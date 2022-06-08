#DOCKER BUILD :    docker build -t rest-application:0.0.1 .
# DOCKER RUN  :      docker run -d -p 8890:8890 -t rest-application:0.0.1

FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=target/rest-application-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} restapp.jar
ENTRYPOINT ["java","-jar","restapp.jar"]
