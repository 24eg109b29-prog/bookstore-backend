# Multi-stage Dockerfile for PageTurner Bookstore
# Stage 1: Build the Spring Boot application
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom.xml and download dependencies (cached layer)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and build production jar
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Minimal runtime image
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Create directory for H2 persistent database
RUN mkdir -p /app/data

# Copy built artifact from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose standard application port
EXPOSE 8080

# Configure environment variables
ENV PORT=8080
ENV SPRING_PROFILES_ACTIVE=prod

# Run application
ENTRYPOINT ["java", "-jar", "app.jar"]
