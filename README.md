# Student Service

A Spring Boot microservice managing student data, integrated with a School service via WebClient for enriched responses. Uses environment variables for configuration, supports CRUD operations, and enables service discovery in a microservice architecture.

## Features

- CRUD operations for student entities.
- Fetches related School data from an external School service.
- Uses Spring WebClient for non-blocking HTTP calls.
- Configuration driven by environment variables loaded from `.env`.
- Built with Spring Boot and integrates with service discovery tools.

## Environment Variables

Your `.env` file should include:

```env
CONNECTION_URI=your_database_connection_uri
PORT_NUMBER=your_service_port
ADDRESS_BASE_URL=http://school-service-base-url
```
Adjust these to fit your deployment environment.

## Usage

- createStudent(Student student): Adds a new student.
- findStudentById(String id): Retrieves student details with associated school info.
- findAllStudents(): Lists all students.
- updateStudentById(String id, Student student): Updates existing student info.

## Technology Stack
- Java 17+
- Spring Boot
- Spring Data JPA
- Spring WebFlux (WebClient)
- Lombok
- dotenv for environment variable management

## Author 
Sandrin Muramutsa
