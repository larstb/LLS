# LLS - Lehrlings Liefer Service

## Developed By:
- Lars-Thorsten Bilek

## Technologien:
- Java (17) - Spring (3.1.4)
- Angular (16)
- Maven
- Docker
- KeyCloak
- Codacy [![Codacy Badge](https://app.codacy.com/project/badge/Grade/aba4c299e4f44bfaaca9864f51c45580)](https://app.codacy.com/gh/larstb/LLS/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)

## Build&Run Backend
- mvn clean install build bootrun
    - mit Tests
- mvn clean install build bootrun -DskipTests
    - ohne Tests

## Build Frontend
- ng serve --open

## Backend Infos
- Port: 9002
- SSL: active

## Local DB (PostgreSQL)
- https://www.baeldung.com/ops/postgresql-docker-setup

## Keycloak Build Command (nur DEV!)
- docker run -d --restart=always --name keycloak -p 9005:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:latest start-dev
- https://www.baeldung.com/postman-keycloak-endpoints
- https://codewithbisky.com/spring-boot-integration-blogs/ep10.html
- Config Endpoint (GET): http://localhost:9005/realms/lls-dev/.well-known/openid-configuration
- Token Endpoint (POST): http://localhost:9005/realms/lls-dev/protocol/openid-connect/token

## Swagger API
- Swagger UI is reachable under https://localhost:9002/api/swagger
- OpenAPI Yaml File is downloadable under https://localhost:9002/v3/api-docs.yaml
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