# Stage 1: Build the Angular app
FROM node:18 AS build
WORKDIR /app

# Copy package.json and package-lock.json
COPY frontend/package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the frontend source code
COPY frontend/ .

# Build the Angular app in production mode
RUN npm run build --configuration=production

# Stage 2: Serve the Angular app using Nginx
FROM nginx:alpine
COPY --from=build /app/dist/frontend /usr/share/nginx/html

# Expose port 80
EXPOSE 80
