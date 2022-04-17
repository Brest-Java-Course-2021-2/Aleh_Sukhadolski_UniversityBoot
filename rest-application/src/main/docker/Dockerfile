FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ADD rest-application-0.0.1-SNAPSHOT.jar restapp.jar
RUN bash -c 'touch /restapp.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/restapp.jar"]