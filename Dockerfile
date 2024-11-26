FROM maven:3.9.6-eclipse-temurin-22-jammy

COPY pom.xml .
COPY src ./src

RUN mvn clean install -DskipTests

FROM openjdk:22-jdk-slim

COPY --from=0 /target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]