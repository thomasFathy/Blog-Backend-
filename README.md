Blog App - Backend API
A robust RESTful Blogging Platform API built with Java 21, Spring Boot 3.4.0, and Spring Security. This application implements stateless JWT-based authentication, layered architecture, and centralized error handling designed for scalable enterprise integration.

🚀 Overview
This project demonstrates clean code practices and solid architectural patterns for enterprise Java backend systems:

Layered Architecture: Clear separation of concerns (Controllers, Services, Repositories).

Stateless Security: Spring Security integrated with JSON Web Tokens (JWT) for secure authorization.

Data Mapping: DTO boundary pattern for clean API contracts and data encapsulation.

Centralized Exception Handling: Standardized @ControllerAdvice error responses across all REST endpoints.

Database Management: Relational database integration using Spring Data JPA and PostgreSQL.

🛠️ Tech Stack & Prerequisites
Language: Java 21

Framework: Spring Boot 3.4.0

Security: Spring Security & JWT

Database: PostgreSQL (managed via Docker)

Build Tool: Maven

Prerequisites
Make sure you have the following installed locally:

JDK 21+

Docker & Docker Compose

⚡ Quick Start
1. Clone the repository
Bash
git clone https://github.com/thomasFathy/Blog-Backend-.git
cd Blog-Backend-
2. Start Database Infrastructure
Spin up the PostgreSQL database and Adminer interface via Docker Compose:

Bash
cd backend
docker compose up -d
3. Run the Spring Boot Backend
Bash
./mvnw spring-boot:run
The backend API will start and serve endpoints at http://localhost:8080.

📂 Key Architecture Features
JWT Authentication Filter: Intercepts incoming requests, validates tokens via SecurityContextHolder, and binds user authentication to the current execution thread.

Role-Based Access Control (RBAC): Restricts restricted endpoints based on user roles and permissions.

DTO Boundary Pattern: Completely isolates database entities from API request/response contracts.

📝 License
This project is licensed under the MIT License.
