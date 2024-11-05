# Utiliser une image de base avec JDK 17
FROM openjdk:17-jdk-alpine

# Créer un répertoire pour l'application
WORKDIR /app

# Copier tous les fichiers de votre projet dans le répertoire de travail du conteneur
COPY . /app

# Construire l'application (exemple pour un projet utilisant Maven)
RUN ./mvnw package -DskipTests

# Définir le fichier JAR qui sera exécuté
# Remplacez "app.jar" par le nom du fichier JAR généré par votre application
CMD ["java", "-jar", "target/app.jar"]

