
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

Check out the live deployed backend

👉 Electro-Route Backend -  [Live](https://electro-route-latest.onrender.com) 


📋 Getting Started

Prerequisites

- Docker & Docker Compose installed
- Java 17+
- Gradle installed or use the Gradle wrapper

Setup and Run

1. Clone the repo:
   git clone https://github.com/ShubhamRaut280/electroroute.git
   cd electroroute

2. Add MySQL db url in application.properties:

3. Build and run the application:
   ./gradlew clean build
   ./gradlew bootRun

4. Open Swagger UI at:
   http://localhost:8080/docs




🤝 Contribution

Contributions, issues, and feature requests are welcome!


## 👨‍💻 Author

Built by [Shubham Raut](https://github.com/ShubhamRaut280)


📄 License

MIT License
