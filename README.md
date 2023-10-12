# LLS - Lehrlings Liefer Service

## Developed By:
- Lars-Thorsten Bilek

## Technologien:
- Java (17) - Spring (3.1.4)
- Angular (16)
- Maven
- Docker
- KeyCloak
- Codacy

## Build&Run Backend
- mvn clean install build bootrun
    - mit Tests
- mvn clean install build bootrun -DskipTests
    - ohne Tests

## Build Frontend
- ng serve --open

## Local DB (PostgreSQL)
- https://www.baeldung.com/ops/postgresql-docker-setup

## Swagger API
- Swagger UI is reachable under localhost:8080/api/swagger
- it is configured in application.yml

## TO-DOs
- JPA Auditing testen
- CRUD für alle Entitäten
- Profilbild
- Dashboard
- Passwort vergessen
- Bestellung aufgaben
- Bestellung abschließen durch Paying User
- Zufällige zweite Person auslosen
- Umfragen
- Default Bestellungen

# MERKEN
- https://medium.com/upstate-interactive/create-a-login-feature-with-ngrx-in-6-steps-8691395bcda7
- https://mherman.org/blog/authentication-in-angular-with-ngrx/
- https://shzhangji.com/blog/2023/01/15/restful-api-authentication-with-spring-security/