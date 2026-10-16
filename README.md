# CircuNet Backend

Spring Boot 3 / Java 17+ backend scaffold for the CircuNet industrial by-product exchange platform. API base path: `/api/v1`. No pagination is used.

## Run locally (H2)
1. Install Java 17+ and Maven 3.9+.
2. From this folder run `mvn spring-boot:run`.
3. Open Swagger UI: http://localhost:8080/swagger-ui.html

H2 data is stored under `./data`. For PostgreSQL, copy `.env.example` to `.env`, set the variables in your shell, start `docker compose up -d`, then run with `DB_URL=jdbc:postgresql://localhost:5432/circunet DB_USERNAME=circunet DB_PASSWORD=change-me DB_DRIVER=org.postgresql.Driver mvn spring-boot:run` (PowerShell users should set environment variables separately).

## Seed accounts
All seeded demo accounts use password `password` for local demo purposes. Admin: `admin@circunet.local`. Other seeded accounts include Nagpur, Pune, Mumbai, Hyderabad, Raipur and Ahmedabad. Change these passwords and replace all illustrative emissions factors before any deployment.

## Example requests
```bash
curl http://localhost:8080/api/v1/materials
curl http://localhost:8080/api/v1/listings
curl -X POST http://localhost:8080/api/v1/auth/login -H 'Content-Type: application/json' -d '{"email":"admin@circunet.local","password":"password"}'
```
After login, pass `Authorization: Bearer <accessToken>` to protected endpoints.

## Important implementation notes
- JWT access tokens expire after 30 minutes and refresh tokens after 7 days. Login attempts table exists, but the 5-failures/15-minute lockout is not yet wired into the login controller.
- Implemented endpoints cover auth, company profile/residues/needs, materials read, listings CRUD/status/certificate, requirements CRUD, feasibility, basic rule-based matches, exchange transitions, ratings, impact aggregates, and parts of admin management.
- The API is a functional starter, not yet a fully verified 1:1 implementation of every item in the supplied specification. Some response shapes and validation details remain to be completed; run `mvn test` and exercise endpoints in Swagger before relying on it. In particular, complete-exchange snapshot calculations, some admin CRUD operations, every compatibility/matching hard filter, strict bean validation across all DTOs, and the full requested integration test suite need follow-up work.
- Compatibility rules and emission factors are illustrative. Replace emission values with cited sources (including GLEC India transport factors) before using results for reporting.
- Certificate files are stored locally in `./uploads`; configure a safe reverse proxy/static file strategy before deployment.
