# Use a lightweight base image with JDK
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the generated JAR file from the target directory
COPY target/Foyer-0.0.1-SNAPSHOT.jar /Foyer-0.0.1-SNAPSHOT.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "/Foyer-0.0.1-SNAPSHOT.jar"]
