FROM openjdk:11-jdk

ENV POSTGRESQL_HOST postgresql

COPY omni-demo-application/target/omni-demo-application-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "/app.jar"]
