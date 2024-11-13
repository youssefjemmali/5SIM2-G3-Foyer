# Stage 1: Build the application
FROM maven:3.9.9-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Copy only the JAR to a clean eclipse image
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/target/Foyer-0.0.1-SNAPSHOT.jar /app/5SIM2-G3-Foyer.jar
EXPOSE 8089
CMD ["java", "-jar", "/app/5SIM2-G3-Foyer.jar"]

