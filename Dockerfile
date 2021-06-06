FROM amazoncorretto:11-alpine-jdk
MAINTAINER michaelhopkins
COPY target/cakemanager-0.0.1-SNAPSHOT.jar cakemanager-1.0.0.jar
ENTRYPOINT ["java","-jar","/cakemanager-1.0.0.jar"]