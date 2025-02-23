# Unique ID Generation Service

A simple Spring Boot-based microservice for generating unique IDs using Java, Spring Data JPA, and MySQL.

## Overview

This microservice generates unique identifiers (IDs) leveraging a database-managed sequence. It provides a RESTful endpoint to retrieve these unique IDs.

## How It Works

### Database Sequence
- The microservice uses a MySQL database sequence (`unique_seq`) to generate incremental, unique identifiers.
- Each call to the `/api/unique-ids/generate` endpoint inserts a new record into the database, where the sequence automatically generates a unique number.

### API Endpoint
- **POST** `/api/unique-ids/generate`: Creates and returns a new unique ID.

### Example Response
```json
{
    "id": 101,
    "createdAt": "2025-02-23T12:34:56"
}
```

## Microservice Architecture Consideration

### Single Database Approach

In a microservice architecture, if multiple instances of this service share the same database:
- Unique IDs are guaranteed since the database sequence handles concurrent access atomically.
- It's safe and thread-proof against simultaneous requests from multiple services.

### Limitations in Distributed Databases

If services use **separate databases**, this sequence-based approach will not guarantee uniqueness across distributed databases, leading to potential ID collisions.

## Alternative Approach: UUIDs

To achieve uniqueness across distributed databases, consider using UUIDs:

```java
@Id
@GeneratedValue(strategy = GenerationType.UUID)
private UUID id;
```

UUIDs are globally unique identifiers that provide collision resistance in distributed environments without relying on a central sequence.

## Tech Stack
- Java 17+
- Spring Boot 3.x
- Spring Data JPA
- MySQL
- Docker (optional, for MySQL setup)

## Setup and Run

1. **Start MySQL Database (Docker Compose)**

```bash
docker-compose up -d
```

2. **Configure application.properties**

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=user
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
```

3. **Run the Spring Boot application**

```bash
mvn spring-boot:run
```

## Testing the API

```bash
curl -X POST http://localhost:8080/api/unique-ids/generate
```

## License

[MIT](LICENSE)

