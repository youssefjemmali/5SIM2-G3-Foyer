FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY . app.jar

EXPOSE 8089

ENTRYPOINT ["java", "-jar", "app.jar"]

