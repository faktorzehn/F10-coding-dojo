FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/battleships-validator-0.0.1-SNAPSHOT.jar /app.jar

# Expose the port the app runs on
EXPOSE 8080

# Define the command to run the app
ENTRYPOINT ["java", "-jar", "/app.jar"]