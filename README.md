# Student API – Spring Boot + MongoDB (Dockerized)

A simple RESTful API for managing student records, built with Spring Boot and MongoDB. This project is fully Dockerized for easy local setup and deployment.

---

## Features

- Java 21 with Spring Boot
- MongoDB for data persistence
- Clean REST API with standard CRUD operations
- Dockerized with multi-stage builds
- Swagger UI for interactive API documentation

---

## Project Structure

.
├── src/ # Java source code
├── pom.xml # Maven build file
├── Dockerfile # Multi-stage Docker build
├── docker-compose.yml # Docker Compose setup
├── .dockerignore # Ignore files for Docker build context
└── README.md # Project documentation

---

## Prerequisites

- [Docker](https://www.docker.com/products/docker-desktop)
- [Docker Compose](https://docs.docker.com/compose/)
- Optional (for development outside Docker): Java 21 & Maven

---

## 🛠️ Setup Instructions

### 🔨 Build & Run with Docker Compose

```bash
# Clone this repo
git clone https://github.com/yourusername/student-api.git
cd student-api

# Build and run the containers
docker compose up -d --build

# Rebuild Only the API
docker compose build api
docker compose up -d

# Stop the stack
docker compose down
```
API Endpoints
Base URL: http://localhost:8080

Method	Endpoint	Description
GET	/students	Get all students
GET	/students/{id}	Get student by ID
POST	/students	Create a student
PUT	/students/{id}	Update a student
DELETE	/students/{id}	Delete a student

Modify this list as per your controller implementation.

Swagger / OpenAPI UI
This project uses springdoc-openapi to auto-generate documentation.

Once running, open:

http://localhost:8080/swagger-ui/index.html

Use it to:

Explore available endpoints

Try requests interactively

See schemas and example payloads

Running Tests
You can run unit/integration tests locally:

```bash
mvn test
# By default, tests are skipped in Docker builds using: -DskipTests
```

Environment Variables
In docker-compose.yml:

```bash
SPRING_DATA_MONGODB_URI: mongodb://mongo:27017/studentdb
SPRING_PROFILES_ACTIVE: docker
#You can create src/main/resources/application-docker.yml to override configuration in Docker.
```

Docker Details
Dockerfile

Multi-stage build:

Stage 1: maven:3.9-eclipse-temurin-21 builds the JAR

Stage 2: eclipse-temurin:21-jre-alpine runs the JAR

docker-compose.yml
Starts MongoDB + the Spring Boot app

Exposes ports 27017 (Mongo) and 8080 (API)

Useful Docker Commands:
```bash
# View logs	
docker compose logs -f

# Check running containers	
docker compose ps

# Rebuild only API image	
docker compose build api

# Enter Mongo shell	
docker exec -it mongo mongosh

```

Tips:
Don’t hardcode versions in COPY — use COPY target/*.jar app.jar

Use .dockerignore to speed up builds and avoid copying unnecessary files

Swagger is auto-configured — no manual setup needed

# License
MIT — Free to use, modify, and distribute.

# Credits
Created with ❤️ by MUKESH SINGH (https://github.com/mukeshmjr)