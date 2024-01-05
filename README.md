# LLS - Lehrlings Liefer Service (Apprentice Delivery System)

## Developed By:
- Lars-Thorsten Bilek

## Technologies:
- Java (17) - Spring (3.2.0)
- Angular (17)
- Maven
- Docker
- KeyCloak
- Codacy [![Codacy Badge](https://app.codacy.com/project/badge/Grade/aba4c299e4f44bfaaca9864f51c45580)](https://app.codacy.com/gh/larstb/LLS/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)

## Backend
- build process
  - mvn clean install build bootrun
      - with tests
  - mvn clean install build bootrun -DskipTests
      - without Tests
- info
  - port: 9002
  - ssl is active

## Backend
- build process
  - ng serve --open
- info
  - port: 4002

## Local DB (PostgreSQL)
- https://www.baeldung.com/ops/postgresql-docker-setup

## Keycloak Commands and Apis
- docker run -d --restart=always --name keycloak -p 9005:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:latest start-dev
- https://www.baeldung.com/postman-keycloak-endpoints
- https://codewithbisky.com/spring-boot-integration-blogs/ep10.html
- Config Endpoint (GET): http://localhost:9005/realms/lls-dev/.well-known/openid-configuration
- Token Endpoint (POST): http://localhost:9005/realms/lls-dev/protocol/openid-connect/token

## Swagger API
- Swagger UI is reachable under https://localhost:9002/api/swagger
- OpenAPI Yaml File is downloadable under https://localhost:9002/v3/api-docs.yaml
- it is configured in application.yml

## Angular Good Links
- https://blog.angular-university.io/angular-material-data-table/

## TO-DOs
- CRUD für alle Entitäten
- Profilbild
- Dashboard
- Passwort vergessen
- Bestellung aufgaben
- Bestellung abschließen durch Paying User
- Zufällige zweite Person auslosen
- Umfragen
- Default Bestellungen
- Remove Google Api Calls for Font and Icons
- Responsive