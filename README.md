# Airline API

## Overview

Airline API is a Spring Boot REST API for managing flight information.

It supports CRUD operations, request validation, error handling, and PostgreSQL persistence.

I built this project to strengthen my skills in Java backend development, REST API design, and database integration.

## Features

- Create flight information
- Retrieve all flights or a flight by flight number
- Update existing flight information
- Delete flight information
- Validate incoming requests
- Handle validation and not-found errors
- Store flight data in PostgreSQL
- Return appropriate HTTP status codes

## Tech Stack

- **Language:** Java
- **Framework:** Spring Boot
- **ORM / Data Access:** Spring Data JPA, Hibernate
- **Database:** PostgreSQL
- **Validation:** Bean Validation
- **Testing:** JUnit
- **Build Tool:** Maven
- **Version Control:** Git, GitHub
- **API:** REST API

## API Endpoints

| Method | Endpoint | Description | Success Status |
|---|---|---|---|
| GET | `/flights` | Retrieve all flights | `200 OK` |
| POST | `/flights` | Create a new flight | `201 Created` |
| GET | `/flights/{flightNum}` | Retrieve a flight by flight number | `200 OK` |
| PUT | `/flights/{flightNum}` | Update an existing flight | `200 OK` |
| DELETE | `/flights/{flightNum}` | Delete a flight | `204 No Content` |

## Validation & Error Handling

The API validates incoming requests and returns `400 Bad Request` when validation fails.

When a flight is not found, the API returns `404 Not Found` using a custom `FlightNotFoundException`.

## Database

PostgreSQL is used as the relational database for storing flight information.

Spring Data JPA and Hibernate are used for data persistence and object-relational mapping (ORM). The `Flight` entity is stored in the `aviation.flights` table and accessed through the repository layer.

## How to Run

### Prerequisites

- Java 21 or later
- PostgreSQL

The Maven Wrapper is included in this repository, so Maven does not need to be installed separately.

### Setup

1. Clone the repository.

```bash
git clone https://github.com/Emi-k0608/airline-api.git
cd airline-api
```

2. Create the PostgreSQL database.

```bash
createdb airline_db
```

Alternatively, you can create it using `psql`.

3. Run the database initialization script.

```bash
psql -d airline_db -f database/init.sql
```

This creates the `aviation` schema and the `flights` table.

4. Set the database credentials.

```bash
export DB_USERNAME=your_postgresql_username
export DB_PASSWORD=your_postgresql_password
```

If your local PostgreSQL user does not require a password:

```bash
export DB_PASSWORD=''
```

By default, the application connects to:

```text
jdbc:postgresql://localhost:5432/airline_db
```

You can optionally override the database URL:

```bash
export DB_URL=jdbc:postgresql://your-database-host:5432/airline_db
```

5. Run the tests.

```bash
./mvnw test
```

6. Run the application.

```bash
./mvnw spring-boot:run
```

The API will be available at:

```text
http://localhost:8080
```

## Running with Docker

Make sure PostgreSQL is running and the database environment variables are configured as described in the Setup section.

### Build the Application

First, build the JAR file:

```bash
./mvnw clean package
```

### Build the Docker Image

```bash
docker build -t airline-api:v0 .
```

### Run the Docker Container

Make sure PostgreSQL is running on your local machine.

```bash
docker run --rm --name airline-api-test \
  -p 8080:8080 \
  -e DB_URL=jdbc:postgresql://host.docker.internal:5432/airline_db \
  -e DB_USERNAME=your_postgresql_username \
  -e DB_PASSWORD=your_postgresql_password \
  airline-api:v0
```

Replace the username and password with your PostgreSQL credentials.

For local PostgreSQL users without a password, use `-e DB_PASSWORD=''`.

`host.docker.internal` allows the Docker container to connect to PostgreSQL running on the host machine when using Docker Desktop.

The API will be available at:

```text
http://localhost:8080/flights
```

## Future Improvements

- Deploy the Spring Boot application to AWS EC2
- Connect the application to PostgreSQL on Amazon RDS
- Expand automated tests for controller and service layers
- Improve error responses with a consistent JSON format
- Add API documentation using Swagger / OpenAPI
- Add database migration management with Flyway