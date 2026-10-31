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
- **Testing:** JUnit, Mockito
- **Containerization:** Docker, Docker Compose
- **Build Tool:** Maven (Maven Wrapper included)
- **Version Control:** Git, GitHub
- **API:** REST API

## API Endpoints

| Method | Endpoint | Description | Success Status |
| --- | --- | --- | --- |
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

## Testing

This project uses JUnit and Mockito for automated testing.

Mockito is used to mock the repository layer, allowing `FlightService` to be tested without connecting to a database.

### Unit Tests

`FlightServiceTest` includes three unit tests:

- `findFlightByNumber_success`: Verifies that an existing flight is returned.
- `findFlightByNumber_notFound`: Verifies that `FlightNotFoundException` is thrown when a flight does not exist.
- `addFlight_success`: Verifies that `addFlight` calls the repository's `save` method and returns the expected result.

### Run FlightService Unit Tests

```bash
./mvnw -Dtest=FlightServiceTest test
```

These tests use a mocked `FlightRepository` and do not require a running PostgreSQL database.

### Run All Tests

```bash
./mvnw test
```

This command also runs `AirlineApiApplicationTests`, which starts the Spring Boot application context and requires a working PostgreSQL connection with the current project configuration.

Make sure the database is running and `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD` are configured appropriately before running all tests. See the setup options below.

## How to Run (Local PostgreSQL)

### Prerequisites

- Java 21 or later
- PostgreSQL installed and running locally

The Maven Wrapper is included in this repository, so Maven does not need to be installed separately.

### Setup

**1. Clone the repository.**

```bash
git clone https://github.com/Emi-k0608/airline-api.git
cd airline-api
```

**2. Create the PostgreSQL database.**

```bash
createdb airline_db
```

Alternatively, create it using `psql`.

**3. Run the database initialization script.**

```bash
psql -d airline_db -f database/init.sql
```

This creates the `aviation` schema and the `flights` table.

**4. Set the database credentials.**

```bash
export DB_USERNAME=your_postgresql_username
export DB_PASSWORD=your_postgresql_password
```

If your local PostgreSQL user does not require a password, use:

```bash
export DB_PASSWORD=''
```

By default, the application connects to:

```text
jdbc:postgresql://localhost:5432/airline_db
```

You can override the database URL if your database uses a different host or port:

```bash
export DB_URL=jdbc:postgresql://your-database-host:5432/airline_db
```

**5. Run the tests.**

```bash
./mvnw test
```

**6. Run the application.**

```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080/flights`.

## Running with Docker (Standalone Application)

This option runs the Spring Boot application in a Docker container while PostgreSQL runs on your Mac (or another host accessible from Docker). Make sure the local database is running and your credentials are configured as described above.

Do not run this standalone container at the same time as the Docker Compose application if both use host port `8080`.

### Build the Application

```bash
./mvnw clean package
```

### Build the Docker Image

```bash
docker build -t airline-api:v0 .
```

### Run the Docker Container

The following example uses Docker Desktop's `host.docker.internal` hostname to connect from the application container to PostgreSQL on the host.

```bash
docker run --rm --name airline-api-test \
  -p 8080:8080 \
  -e DB_URL=jdbc:postgresql://host.docker.internal:5432/airline_db \
  -e DB_USERNAME \
  -e DB_PASSWORD \
  airline-api:v0
```

The `-e DB_USERNAME` and `-e DB_PASSWORD` options pass the variables already exported in your terminal to the container. Do not put actual credentials in this README.

The API will be available at `http://localhost:8080/flights`.

## Running with Docker Compose

**Prerequisites:** Docker Desktop with Docker Compose, Java 21 or later, and the included Maven Wrapper. A separate PostgreSQL installation is not required.

Docker Compose starts the Spring Boot application and a PostgreSQL database in separate containers. The application connects to the database using the Docker service hostname `db`.

### 1. Create the Environment File

```bash
cp .env.example .env
```

Edit `.env` and set a secure value for `POSTGRES_PASSWORD`. **Do not commit `.env` to Git.**

### 2. Build the Application

Build the JAR file used by the Docker image:

```bash
./mvnw clean package -DskipTests
```

`-DskipTests` is used here because running the full test suite also requires database access. Run the tests separately once PostgreSQL is available, as described below.

### 3. Start the Containers

```bash
docker compose up -d --build
```

Docker Compose starts PostgreSQL and waits for its health check before starting Spring Boot.

### 4. Check the Containers

```bash
docker compose ps
```

Both services should be running, and the PostgreSQL service should report a healthy status.

### 5. Check the API

```bash
curl -i http://localhost:8080/flights
```

Expected status: `200 OK`. An empty list (`[]`) is valid if no flights have been added.

### 6. View Logs

```bash
docker compose logs --tail=50 app
```

To inspect database logs, use:

```bash
docker compose logs --tail=50 db
```

### Run Maven Tests Using Docker PostgreSQL

In `compose.yaml`, the PostgreSQL container's port `5432` is published as port `5433` on the host's loopback interface:

```yaml
services:
  db:
    ports:
      - "127.0.0.1:5433:5432"
```

This is an excerpt showing the port configuration; keep the other `db` settings in `compose.yaml` unchanged.

- **Inside Docker Compose**, the application connects to `db:5432`.
- **From your Mac**, Maven connects to `127.0.0.1:5433`.

Make sure the database container is running. Then, in the same terminal where you will run Maven, set the connection variables:

```bash
export DB_URL=jdbc:postgresql://127.0.0.1:5433/airline_db
export DB_USERNAME=airline_user
```

The value of `DB_PASSWORD` must match `POSTGRES_PASSWORD` in your `.env` file. If `.env` is a trusted, shell-compatible local file, you can load it with:

```bash
set -a
source .env
set +a
export DB_PASSWORD="$POSTGRES_PASSWORD"
```

Do not share or commit the actual password. The commands above are intended for a macOS shell such as zsh.

Now run all tests:

```bash
./mvnw test
```

Expected result: `BUILD SUCCESS` when all tests pass.

### Smoke Test

After starting the containers, use `curl` to verify the API's primary operations. These commands create and then delete a test flight with number `AC123`. Use a different flight number if `AC123` already exists.

**1. Retrieve all flights**

```bash
curl -i http://localhost:8080/flights
```

Expected status: `200 OK`.

**2. Create a flight**

```bash
curl -i -X POST http://localhost:8080/flights \
  -H "Content-Type: application/json" \
  -d '{"flightNum":"AC123","origin":"YVR","destination":"YYZ","status":"ON_TIME"}'
```

Expected status: `201 Created`.

**3. Retrieve the created flight**

```bash
curl -i http://localhost:8080/flights/AC123
```

Expected status: `200 OK`.

**4. Update the flight**

```bash
curl -i -X PUT http://localhost:8080/flights/AC123 \
  -H "Content-Type: application/json" \
  -d '{"flightNum":"AC123","origin":"YVR","destination":"YYC","status":"DELAYED"}'
```

Expected status: `200 OK`.

**5. Delete the flight**

```bash
curl -i -X DELETE http://localhost:8080/flights/AC123
```

Expected status: `204 No Content`.

**6. Verify deletion**

```bash
curl -i http://localhost:8080/flights/AC123
```

Expected status: `404 Not Found`.

### 7. Stop the Containers

When you have finished testing, stop the containers:

```bash
docker compose down
```

The PostgreSQL named volume is preserved by default. Avoid using `docker compose down -v` unless you intentionally want to delete the database data.

## Future Improvements

- Deploy the Spring Boot application to AWS EC2
- Connect the application to PostgreSQL on Amazon RDS
- Expand unit tests for remaining `FlightService` methods and add controller tests
- Improve error responses with a consistent JSON format
- Add API documentation using Swagger / OpenAPI
- Add database migration management with Flyway
