FROM maven:3-eclipse-temurin-21 AS builder
# FROM openjdk:21-jdk-bullseye AS builder

LABEL MAINTAINER="wsc@ibf.ssf"
LABEL APPLICATION="SSF Day 17 Lecture"

## BUILD 1st stage
WORKDIR /app

# Copy all required files into the app folder in the image
COPY  mvnw.cmd .
COPY  mvnw .
COPY  pom.xml .
COPY  .mvn .mvn
COPY  src src

# Run Maven command to build package, exclude unit testing
# DL Maven dependencies and build jar file into target folder
RUN mvn package -Dmaven.test.skip=true


## BUILD/RUN 2nd container
FROM maven:3-eclipse-temurin-21

# Dir to contain src and target
WORKDIR /app_run

COPY --from=builder /app/target/day17-0.0.1-SNAPSHOT.jar weather.jar
# COPY --from=builder /app/target/day17-0.0.1-SNAPSHOT.jar .     // or preserve the filename


## RUN stage
# same port as defined in application.properties
ENV OPENWEATHERMAP_KEY=xxx PORT=8080
EXPOSE ${PORT}

ENV SPRING_DATA_REDIS_HOST=localhost
ENV SPRING_DATA_REDIS_PORT=6379
ENV SPRING_DATA_REDIS_USERNAME=NOT_SET
ENV SPRING_DATA_REDIS_PASSWORD=NOT_SET

# Day18 Healthcheck
HEALTHCHECK --interval=30s --timeout=5s --start-period=5s --retries=3 \
        CMD curl http://127.0.0.1:${PORT}/healthz || exit 1

# target/<name in pom>-<version>.jar
ENTRYPOINT SERVER_PORT=${PORT} java -jar target/weather.jar