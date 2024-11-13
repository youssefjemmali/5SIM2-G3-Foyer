# Start with a base image that includes Java (use the version your project requires)
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app
# Expose the port your application runs on
EXPOSE 8080


COPY target/Foyer-0.0.1.jar /Foyer-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/Foyer-0.0.1.jar"]
