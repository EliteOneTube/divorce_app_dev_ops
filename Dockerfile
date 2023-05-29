# Build stage
FROM maven:3.8.7-openjdk-18-slim AS build

COPY ./ ./

RUN mvn -X -f pom.xml clean package -DskipTests

# Run stage
FROM openjdk:17 AS run
FROM build AS build
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
