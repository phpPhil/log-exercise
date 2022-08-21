FROM amazoncorretto:18-alpine-jdk
COPY target/log-exercise-0.0.1-SNAPSHOT.jar log-exercise.jar
ENTRYPOINT ["java","-jar","/log-exercise.jar"]