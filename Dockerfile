FROM amazoncorretto:17-alpine-jdk
MAINTAINER ARMANDO
COPY target/gimnasio-0.0.1-SNAPSHOT.jar gimnasio-app.jar 
ENTRYPOINT ["java","-jar","/gimnasio-app.jar"]