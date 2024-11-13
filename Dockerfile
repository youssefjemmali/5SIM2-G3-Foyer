# Start with a base image that includes Java (use the version your project requires)
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
# Make sure your JAR file is generated in the "target" folder by Maven or Gradle
COPY target/your-app.jar app.jar

# Expose the port your application runs on
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
