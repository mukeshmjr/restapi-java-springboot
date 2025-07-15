# ── Build stage ───────────────────────────────────────
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom first to leverage Docker layer cache for deps
COPY pom.xml .
RUN mvn -B dependency:go-offline

# Copy source and build the Jar
COPY src ./src
RUN mvn -B package -DskipTests

# ── Runtime stage ─────────────────────────────────────
FROM eclipse-temurin:21-jre-alpine AS app
WORKDIR /app

# Copy the built artifact from the build stage
COPY --from=build /app/target/*.jar app.jar

# Optional: activate a specific Spring profile
# ENV SPRING_PROFILES_ACTIVE=docker

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
