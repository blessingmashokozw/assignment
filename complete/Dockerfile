FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy JAR file
COPY build/libs/*.jar app.jar

# Copy environment file if it exists
COPY .env .env

# Set environment variables from .env file
RUN if [ -f .env ]; then export $(cat .env | xargs); fi

EXPOSE 8080

# Pass environment variables to the application
ENTRYPOINT ["sh", "-c", "if [ -f .env ]; then export $(cat .env | xargs); fi; java -jar app.jar"]
