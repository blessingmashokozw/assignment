FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy JAR file
COPY build/libs/*.jar app.jar

# Copy environment file from deployment directory
ARG APP_ENV
ENV APP_ENVIRONMENT=${APP_ENV:-unknown}

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
