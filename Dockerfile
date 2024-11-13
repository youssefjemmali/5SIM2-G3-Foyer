FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY target/5SIM2-G3-Foyer-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8089

ENTRYPOINT ["java", "-jar", "app.jar"]

