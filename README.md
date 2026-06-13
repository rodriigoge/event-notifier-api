# Event Notifier API

API for registering and processing asynchronous notifications via `EMAIL`, `SMS`, and `WEBHOOK`, built with Java 25 and Spring Boot 4.

## Stack

- Java 25
- Spring Boot 4.0.5
- Spring Data JPA
- PostgreSQL 16
- Flyway
- RabbitMQ
- Springdoc OpenAPI
- Micrometer / Prometheus

## Requirements

- JDK 25
- Docker Desktop
- Maven 3.9+ or IDE execution

## Local Infrastructure

Start the local dependencies with Docker:

```powershell
docker compose up -d
```

Services used in the local environment:

- PostgreSQL: `localhost:5433`
- RabbitMQ AMQP: `localhost:5672`
- RabbitMQ Management: [http://localhost:15672](http://localhost:15672)
- Grafana: [http://localhost:3000](http://localhost:3000)

Default credentials:

- PostgreSQL
  - database: `event_notifier`
  - username: `event`
  - password: `event`
- RabbitMQ
  - username: `guest`
  - password: `guest`

## Running the Application

The project requires Java 25. If your terminal is pointing to another JDK, set `JAVA_HOME` before starting the app.

Example in PowerShell:

```powershell
$env:JAVA_HOME="C:\Users\rodri\.jdks\openjdk-25.0.1"
$env:Path="$env:JAVA_HOME\bin;$env:Path"
```

Run with Maven:

```powershell
mvn spring-boot:run
```

Current default application port:

- API: [http://localhost:8082](http://localhost:8082)

To run on a different port:

```powershell
$env:PORT=8083
mvn spring-boot:run
```

## Configuration

The main configuration is in [application.yml](C:/dev/event-notifier-api/src/main/resources/application.yml).

Default values:

- `DATASOURCE_URL=jdbc:postgresql://localhost:5433/event_notifier`
- `DATASOURCE_USERNAME=event`
- `DATASOURCE_PASSWORD=event`
- `PORT=8082`

## Security

The following endpoints are publicly accessible:

- `GET /actuator/health`
- `POST /notifications`

All other endpoints remain protected by the default Spring Security configuration.

## Main Endpoints

### Health Check

```http
GET /actuator/health
```

Expected response:

```json
{
  "status": "UP"
}
```

### Create Notification

```http
POST /notifications
Content-Type: application/json
```

Example request body:

```json
{
  "type": "EMAIL",
  "payload": "{\"message\":\"Notification test\",\"recipient\":\"demo@example.com\"}"
}
```

Expected response:

```json
{
  "id": "uuid",
  "status": "PENDING",
  "createdAt": "2026-04-04T19:17:19"
}
```

Accepted `type` values:

- `EMAIL`
- `SMS`
- `WEBHOOK`

## Testing the API

### PowerShell Script

A test script is available at [test-api.ps1](C:/dev/event-notifier-api/scripts/test-api.ps1).

Example:

```powershell
.\scripts\test-api.ps1 -BaseUrl "http://localhost:8082"
```

The script performs:

- `GET /actuator/health`
- `POST /notifications`

### Postman

A ready-to-import collection is available at [event-notifier-api.postman_collection.json](C:/dev/event-notifier-api/postman/event-notifier-api.postman_collection.json).

Steps:

1. Import the collection into Postman.
2. Set the `baseUrl` variable to `http://localhost:8082`.
3. Run the requests:
   - `Health`
   - `Create Notification - Email`
   - `Create Notification - SMS`
   - `Create Notification - Webhook`

## Request Flow

1. The API receives a request on `POST /notifications`.
2. The notification is persisted in PostgreSQL.
3. A `NotificationCreatedEvent` is published to RabbitMQ.
4. The consumer processes the message and routes it to the matching channel.
5. The notification status is updated to `SENT` or `FAILED`.

## Development Notes

- Flyway is enabled and creates the `notifications` table.
- The `payload` field is stored as `jsonb` in PostgreSQL.
- RabbitMQ uses JSON conversion to publish and consume `NotificationCreatedEvent`.
- If the application port is already in use, set `PORT` before starting the app.
  
---
