# Étape 1 : Construction de l'application
FROM node:14 AS build
WORKDIR /app

# Copier les fichiers package.json et package-lock.json pour installer les dépendances
COPY package*.json ./

# Installer les dépendances
RUN npm install

# Copier tout le code source dans le conteneur
COPY . .

# Construire l'application pour la production
RUN npm run build --prod

# Étape 2 : Utiliser Nginx pour servir les fichiers construits
FROM nginx:alpine
COPY --from=build /app/dist/frontend /usr/share/nginx/html

# Exposer le port 80 pour accéder à l'application
EXPOSE 80

# Commande par défaut pour lancer Nginx
CMD ["nginx", "-g", "daemon off;"]
