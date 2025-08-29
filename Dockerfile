FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/spring-security-jwt-0.0.1-SNAPSHOT.jar /app/spring-security-jwt.jar

EXPOSE 8081

CMD ["java", "-jar", "/app/spring-security-jwt.jar"]

