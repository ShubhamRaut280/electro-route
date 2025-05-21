⚡ ElectroRoute

ElectroRoute is a secure, containerized Spring Boot backend application built for managing electric vehicle (EV) charging stations and users. It features JWT authentication, MySQL persistence, and allows users to find nearby charging stations based on location.

🚀 Features

- User Management with JWT authentication and Spring Security
- Charging Station CRUD with location and pricing details
- Find Nearby Stations by geographic coordinates and radius
- MySQL Database running in Docker
- Containerized via Docker and Docker Compose
- Swagger/OpenAPI documentation for all APIs

📦 Tech Stack

- Java 17, Spring Boot
- Spring Security + JWT
- MySQL
- Docker & Docker Compose
- Swagger UI

🌐 Live Demo

Check out the live deployed backend:

👉 Electro-Route Backend - https://electro-route-latest.onrender.com/docs

🐳 Deploy with Docker Image

You can also run the ElectroRoute backend directly from Docker using the published image.

🔗 Docker Hub Image

    docker pull shubham9689/electro-route:latest

🚀 Run the Docker container

Make sure your MySQL database is running (in Docker or locally). Then run:

    docker run -d -p 8080:8080 \
      -e SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/electroroute_db \
      -e SPRING_DATASOURCE_USERNAME=root \
      -e SPRING_DATASOURCE_PASSWORD=yourpassword \
      shubhamraut280/electroroute:latest

✅ Replace host.docker.internal with your DB hostname
✅ Change DB credentials as per your local setup

📋 Getting Started (Dev Setup)

Prerequisites

- Docker & Docker Compose installed
- Java 17+
- Gradle installed or use the Gradle wrapper

Setup and Run (from source)

1. Clone the repo:

       git clone https://github.com/ShubhamRaut280/electroroute.git
       cd electroroute

2. Add MySQL DB URL and credentials in src/main/resources/application.properties

3. Build and run the application:

       ./gradlew clean build
       ./gradlew bootRun

4. Open Swagger UI at:

       http://localhost:8080/docs

🤝 Contribution

Contributions, issues, and feature requests are welcome!
Feel free to fork the repo and submit a pull request.

👨‍💻 Author

Built by Shubham Raut (https://github.com/ShubhamRaut280)

📄 License

This project is licensed under the MIT License.
