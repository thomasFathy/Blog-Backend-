# Blog Platform Backend API

A robust RESTful Blogging Platform API built with Java 17, Spring Boot, Spring Security, and MapStruct. This application implements stateless JWT-based authentication, layered architecture, automated entity-to-DTO mapping, and centralized error handling designed for scalable enterprise integration.

---

## Overview

This project demonstrates clean code practices and solid architectural patterns for enterprise Java backend systems:

* Layered Architecture: Clear separation of concerns (Controllers, Services, Repositories).
* Stateless Security: Spring Security integrated with JJWT (JSON Web Tokens) for secure authorization.
* Automated DTO Mapping: Integrated MapStruct and Lombok annotation processors for fast, type-safe API boundary mapping.
* Centralized Exception Handling: Standardized error responses across all REST endpoints.
* Dual Database Support: Production-ready PostgreSQL integration paired with H2 in-memory database support for rapid local development and testing.

---

## Tech Stack & Prerequisites

* Language: Java 17
* Framework: Spring Boot
* Security: Spring Security & JJWT (0.11.5)
* Object Mapping: MapStruct & Lombok
* Database: PostgreSQL & H2 Database
* Build Tool: Maven

### Prerequisites

Make sure you have the following installed locally:

* JDK 17+
* Docker & Docker Compose

---

## Quick Start

### 1. Clone the repository

git clone https://github.com/thomasFathy/Blog-Backend-.git
cd Blog-Backend-

### 2. Start Database Infrastructure

Spin up the PostgreSQL database container via Docker Compose:

docker compose up -d

### 3. Run the Spring Boot Backend

./mvnw spring-boot:run

The backend API will start and serve endpoints at http://localhost:8080.

---

## Key Architecture Features

* JWT Authentication Filter: Intercepts incoming requests, validates tokens via SecurityContextHolder, and binds user authentication to the current execution thread.
* Annotation Processing Pipeline: Custom Maven compiler plugin setup chaining Lombok and MapStruct processors for clean boilerplate-free entities and DTO mappers.
* Role-Based Access Control (RBAC): Restricts restricted endpoints based on user roles and permissions.

---

## License

This project is licensed under the MIT License.
