# QuantityMeasurementApp

A progressive Java application that demonstrates the evolution of software design — from simple OOP concepts to a fully-fledged **Spring Boot Microservices** architecture with JWT authentication and service discovery.

Built following **Test-Driven Development (TDD)** principles and **SOLID** design patterns, with each use case layered incrementally on the previous one.

---

## 🧭 Project Evolution

| Phase | Use Cases | Description |
|-------|-----------|-------------|
| **Phase 1 — Core OOP** | UC1 – UC2 | Feet & Inches equality using `equals()` override |
| **Phase 2 — Generic Design** | UC3 – UC5 | `LengthUnit` enum, `QuantityLength` class, unit conversion |
| **Phase 3 — Arithmetic** | UC6 – UC7 | Length addition with same and target unit |
| **Phase 4 — Refactoring** | UC8 | Standalone Unit enum with conversion responsibility |
| **Phase 5 — Multi-Type** | UC9 – UC14 | Weight, Volume, Temperature measurement support |
| **Phase 6 — Architecture** | UC15 | Package restructuring and test organization |
| **Phase 7 — Persistence** | UC16 | JDBC-based database integration |
| **Phase 8 — REST API** | UC17 | Spring Boot REST services + Spring Data JPA |
| **Phase 9 — Security** | UC18 | JWT + Google OAuth2 authentication |
| **Phase 10 — Microservices** | UC19 | Spring Cloud microservices with Eureka & API Gateway |

---

## 📋 Use Cases

### UC1 — Feet Measurement Equality
Implements the `Feet` inner class with a proper `equals()` override following Java contracts (reflexive, symmetric, null-safe).

### UC2 — Feet and Inches Equality
Extends UC1 with an `Inches` inner class. Validates same-type measurement equality independently.

### UC3 — Generic Quantity Class (DRY Principle)
Introduces `LengthUnit` enum and a unified `QuantityLength` class. Eliminates duplicate equality logic by converting to a base unit (FEET) for comparison.

```
1 FEET = 12 INCH
```

### UC4 — Extended Unit Support
Adds `YARD` and `CENTIMETER` to `LengthUnit`:
```
1 YARD = 3 FEET = 36 INCH
1 CM   = 0.393701 INCH
```

### UC5 — Unit-to-Unit Conversion
Adds `convertTo(LengthUnit)` and a static `convert(value, from, to)` utility with round-trip precision using `EPSILON = 1e-9`.

### UC6 — Addition of Two Lengths
Implements `add(QuantityLength other)` — converts both operands to FEET, sums, returns result in caller's unit.

### UC7 — Addition with Target Unit
Overloads `add(QuantityLength other, LengthUnit targetUnit)` to return the sum in any specified unit.

### UC8 — Standalone Unit Enum
Refactors `LengthUnit` into its own file with full `toBaseUnit()` / `fromBaseUnit()` conversion responsibility.

### UC9 — Weight Measurement
Introduces `WeightUnit` enum and `QuantityWeight` class:
```
1 KILOGRAM = 1000 GRAM
1 POUND    = 453.592 GRAM
```
Supports equality, conversion, and addition.

### UC10 — Generic Quantity with Interface
Introduces the `IMeasurable` interface and a generic `Quantity<U extends IMeasurable>` class, enabling type-safe multi-category support.

### UC11 — Volume Measurement
Adds `VolumeUnit` enum:
```
1 GALLON     = 3.785 LITRE
1 MILLILITRE = 0.001 LITRE
```

### UC12 — Subtraction and Division
Extends arithmetic operations with `subtract()` and `divide()` on all quantity types.

### UC13 — Centralized Arithmetic Logic
Extracts common arithmetic into a shared utility class (`ArithmeticCapable` interface) enforcing DRY across Length, Weight, and Volume.

### UC14 — Temperature Measurement
Adds `TemperatureUnit` (CELSIUS, FAHRENHEIT, KELVIN) with non-linear conversion formulas. Addition/subtraction not supported (by design — temperature deltas behave differently).

### UC15 — Architecture Refactoring
Restructures the project into proper packages:
```
com.bridgelabz.
├── enums/        (LengthUnit, WeightUnit, VolumeUnit, TemperatureUnit)
├── interfaces/   (IMeasurable, ArithmeticCapable)
├── model/        (QuantityModel)
└── exception/    (QuantityMeasurementException)
```

### UC16 — Database Integration (JDBC)
Integrates **Spring Boot 3.2.5** with a custom JDBC connection pool (`ConnectionPool`) and `AppConfig` for property loading. Persists all measurement operations.

**Stack:** Spring Boot · JDBC · MySQL · Lombok

### UC17 — Spring Boot REST API + JPA
Migrates from raw JDBC to **Spring Data JPA**. Exposes a full REST API:

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/quantity/convert` | Convert between units |
| `GET`  | `/api/quantity/all`     | Get all stored results |
| `DELETE` | `/api/quantity/deleteAll` | Clear all records |

**Stack:** Spring Boot 3.3.5 · Spring Web · Spring Data JPA · H2 / MySQL · Lombok · Swagger (springdoc-openapi)

### UC18 — JWT & Google OAuth2 Authentication
Secures the REST API with:
- **JWT** (JJWT 0.11.5) — stateless token-based authentication
- **Google OAuth2** — social login via Spring Security OAuth2 Client
- Custom `JwtAuthenticationFilter`, `JwtService`, and `OAuth2SuccessHandler`
- User registration & login endpoints under `/api/auth/`

**Stack:** Spring Security · JJWT · OAuth2 · Spring Data JPA · MySQL · Swagger UI

### UC19 — Spring Cloud Microservices
Decomposes the monolith into independent services:

```
QuantityMeasurementApp/
├── eureka-server/       → Service Registry (port 8761)
├── config-server/       → Centralised configuration (port 8888)
├── api-gateway/         → Single entry point with routing (port 8080)
├── auth-service/        → JWT & OAuth2 authentication service
└── quantity-service/    → Core measurement & conversion service
```

**Stack:** Spring Cloud · Netflix Eureka · Spring Cloud Gateway · Spring Cloud Config · Spring Security · MySQL

---

## 🛠️ Tech Stack

| Category | Technology |
|----------|-----------|
| **Language** | Java 17 / 21 |
| **Build Tool** | Apache Maven |
| **Testing** | JUnit 5 (Jupiter) |
| **Framework** | Spring Boot 3.x |
| **Persistence** | JDBC → Spring Data JPA (MySQL, H2) |
| **Security** | Spring Security, JJWT 0.11.5, OAuth2 |
| **Service Discovery** | Netflix Eureka |
| **API Gateway** | Spring Cloud Gateway |
| **Config Management** | Spring Cloud Config Server |
| **API Docs** | Springdoc OpenAPI (Swagger UI) |
| **Utilities** | Lombok |

---

## 🏗️ Design Principles

- ✅ **TDD** — Tests written before implementation for every use case
- ✅ **DRY** — Centralized conversion and arithmetic logic
- ✅ **SOLID** — Interface segregation via `IMeasurable`, `ArithmeticCapable`
- ✅ **Immutability** — All `Quantity` objects are final and return new instances
- ✅ **Enum-driven design** — Unit constants encapsulate their own conversion logic
- ✅ **Layered architecture** — Controller → Service → Repository pattern

---

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Maven 3.8+
- MySQL (for UC16+)
- Docker (optional, for microservices)

### Run (UC1 – UC15 — Plain Maven)
```bash
git checkout feature/UC<N>-<name>
mvn clean test
mvn exec:java -Dexec.mainClass="com.bridgelabz.QuantityMeasurementApp"
```

### Run (UC16 – UC18 — Spring Boot)
```bash
git checkout feature/UC<N>-<name>
# Configure DB in src/main/resources/application.properties
mvn spring-boot:run
```

### Run (UC19 — Microservices)
Start services in order:
```bash
# 1. Config Server
cd config-server && mvn spring-boot:run

# 2. Eureka Server
cd eureka-server && mvn spring-boot:run

# 3. Auth Service
cd auth-service && mvn spring-boot:run

# 4. Quantity Service
cd quantity-service && mvn spring-boot:run

# 5. API Gateway
cd api-gateway && mvn spring-boot:run
```

---

## 🌿 Branch Structure

```
main                          ← Project initialisation only
develop                       ← Stable integration (UC1 – UC16)
feature/UC1-feetChecking
feature/UC2-FeetInchesEquality
feature/UC3-genericQuantity
feature/UC4-extendedUnit
feature/UC5-unitConversion
feature/UC6-addTwoLengths
feature/UC7-addWithTarget
feature/UC8-standAlone
feature/UC9-weightEquality
feature/UC10-genericQuantity
feature/UC11-volumeMeasurement
feature/UC12-addedSubstractionDivision
feature/UC13-centralizedLogic
feature/UC14-temperatureMeasurement
feature/UC15-refactorArchitecture
feature/UC16-dbIntegration
feature/UC17-frameworkIntegration
feature/UC18-authentication
feature/UC19-microservices
```

All feature branches form a **single linear commit chain** — each builds on the previous, demonstrating progressive feature development.

---

## 📁 Package Structure (UC17+)

```
com.bridgelabz/
├── controller/
│   └── QuantityMeasurementController.java
├── dto/
│   ├── QuantityDTO.java
│   └── QuantityMeasurementDTO.java
├── enums/
│   ├── LengthUnit.java
│   ├── WeightUnit.java
│   ├── VolumeUnit.java
│   └── TemperatureUnit.java
├── exception/
│   ├── QuantityMeasurementException.java
│   └── DatabaseException.java
├── interfaces/
│   ├── IMeasurable.java
│   └── ArithmeticCapable.java
├── model/
│   ├── QuantityModel.java
│   └── QuantityMeasurementEntity.java
├── repository/
│   ├── IQuantityMeasurementRepository.java
│   └── QuantityMeasurementRepository.java
├── service/
│   ├── IQuantityMeasurementService.java
│   └── QuantityMeasurementServiceImpl.java
└── util/
    ├── AppConfig.java
    └── ConnectionPool.java
```

---

## 👤 Author

**BL-Sriram**
📧 sriram.jeevakarunyam@bridgelabz.com
🏢 BridgeLabz Solutions

---

## 📄 License

This project is intended for educational and training purposes.