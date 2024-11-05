# Utiliser une image de base avec Alpine et OpenJDK 17
FROM openjdk:17-jdk-alpine


# Créer un répertoire pour l'application
WORKDIR /app

# Copier tous les fichiers de votre projet dans le répertoire de travail du conteneur
COPY . .

# Définir le fichier JAR qui sera exécuté
CMD ["java", "-jar", "target/Foyer-0.0.1-SNAPSHOT.jar"]
