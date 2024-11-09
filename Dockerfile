# Use a base image
FROM openjdk:11-jre

# Copy the app's JAR file into the container
COPY target/myapp.jar /app.jar

# Define the default command to run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
