FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/user-service-admin-0.0.1-SNAPSHOT.jar /app/service.jar

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "/app/service.jar"]