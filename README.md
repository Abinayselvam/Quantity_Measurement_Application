# Quantity Measurement Application — UC17 (Spring Boot + REST + JPA)

This module migrates the standalone Quantity Measurement Application (UC1–UC16)
to a Spring Boot REST service, backed by Spring Data JPA, with centralized
exception handling, Swagger/OpenAPI documentation, Actuator monitoring, and a
security foundation.

## Package layout

```
com.app.quantitymeasurement
├── QuantityMeasurementApplication.java     # Spring Boot entry point
├── Enums/                                  # LengthUnit, WeightUnit, VolumeUnit,
│                                            # TemperatureUnit, ArithmeticOperation, OperationType
├── Interface/                              # IMeasurable, SupportsArithmetic
├── model/                                  # QuantityModel<U> (business logic) +
│                                            # QuantityMeasurementEntity (JPA entity)
├── dto/                                    # QuantityDTO, QuantityInputDTO, QuantityMeasurementDTO
├── repository/                             # QuantityMeasurementRepository (Spring Data JPA)
├── service/                                # IQuantityMeasurementService, QuantityMeasurementServiceImpl
├── controller/                             # QuantityMeasurementController (REST)
├── exception/                              # QuantityMeasurementException, GlobalExceptionHandler
└── config/                                 # SecurityConfig
```

## Running the application

```bash
mvn clean install
mvn spring-boot:run
```

Or build and run the jar:

```bash
mvn clean package
java -jar target/quantity-measurement-app-1.0.0.jar
```

The application starts on an embedded Tomcat server at `http://localhost:8080`.

## Key endpoints

| Method | Path                                              | Description                          |
|--------|----------------------------------------------------|---------------------------------------|
| POST   | `/api/v1/quantities/compare`                       | Compare two quantities                |
| POST   | `/api/v1/quantities/convert`                       | Convert a quantity to another unit    |
| POST   | `/api/v1/quantities/add`                           | Add two quantities                    |
| POST   | `/api/v1/quantities/subtract`                      | Subtract two quantities               |
| POST   | `/api/v1/quantities/divide`                        | Divide two quantities                 |
| GET    | `/api/v1/quantities/history/operation/{operation}` | Operation history                     |
| GET    | `/api/v1/quantities/history/type/{measurementType}`| History filtered by measurement type  |
| GET    | `/api/v1/quantities/history/errored`               | Error history                         |
| GET    | `/api/v1/quantities/count/{operation}`             | Count of successful operations        |

Example request body (`compare`, `add`, `subtract`, `divide`):

```json
{
  "thisQuantityDTO": {"value": 1.0, "unit": "FEET", "measurementType": "LengthUnit"},
  "thatQuantityDTO": {"value": 12.0, "unit": "INCHES", "measurementType": "LengthUnit"}
}
```

`convert` uses `thatQuantityDTO.unit` as the conversion target. `add`/`subtract`
accept an optional `targetUnit` field; if omitted, the unit of `thisQuantityDTO`
is used.

## Dev tools

- H2 console: `http://localhost:8080/h2-console`
  (JDBC URL `jdbc:h2:mem:quantitymeasurementdb`, user `sa`, blank password)
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI spec: `http://localhost:8080/api-docs`
- Actuator health: `http://localhost:8080/actuator/health`

## Profiles

- `application.properties` — default/development (H2, DEBUG logging, Swagger, full Actuator)
- `application-prod.properties` — production (MySQL, WARN logging, restricted Actuator)
  Activate with `-Dspring.profiles.active=prod`.

## Testing

```bash
mvn test                                              # all tests
mvn test -Dtest=QuantityMeasurementControllerTest     # MockMvc controller tests
mvn test -Dtest=QuantityMeasurementApplicationTests    # full integration tests
mvn surefire-report:report                            # rich HTML test report
open target/site/surefire-report.html
```

## Legacy code

The UC1–UC16 JDBC-based classes (`entities`, `repository`, `service`,
`controller`, `factory`, `utils`, standalone `app/Main.java`) have been moved
to `dump/uc16-legacy/` for reference. They are excluded from the build via
`.gitignore` and are not compiled as part of this module.
