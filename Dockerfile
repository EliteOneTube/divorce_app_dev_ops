# Build stage
FROM maven:3.8.7-openjdk-18-slim AS build

WORKDIR /app

COPY pom.xml ./

COPY src ./src

RUN mvn clean package -DskipTests

# Run stage
FROM openjdk:17 AS run
COPY --from=build /app/target/*.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
