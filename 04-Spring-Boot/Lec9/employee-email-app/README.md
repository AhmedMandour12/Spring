# Employee / Email – Spring Boot

Java 17, Spring Boot 3.3.5, Spring Data JPA, Validation, Lombok, MapStruct, H2 (MySQL optional).

Run: `mvn spring-boot:run`
H2 console: http://localhost:8080/h2-console (JDBC URL `jdbc:h2:mem:employeedb`, user `sa`)

## Employee  `/api/employees`
| Method | Path | Notes |
|---|---|---|
| POST | `/api/employees` | create -> 201 |
| POST | `/api/employees/with-emails` | employee + list of emails, saved together -> 201 |
| PUT | `/api/employees/{id}` | update -> 200 |
| DELETE | `/api/employees/{id}` | remove -> 204 |
| GET | `/api/employees` | all |
| GET | `/api/employees/{id}` | by id (404 if missing) |
| GET | `/api/employees/by-ids?ids=1,2,3` | by list of ids |
| GET | `/api/employees/by-names?names=Ali,Sara` | by list of names |

## Email  `/api/emails`
| Method | Path | Notes |
|---|---|---|
| POST | `/api/emails` | create (needs `employeeId`) -> 201 |
| PUT | `/api/emails/{id}` | update -> 200 |
| DELETE | `/api/emails/{id}` | remove -> 204 |
| GET | `/api/emails` | all |
| GET | `/api/emails/by-name?name=gmail` | by name |
| GET | `/api/emails/by-names?names=gmail,yahoo` | by list of names |
| GET | `/api/emails/by-content?content=eslam@gmail.com` | by content |

## Sample bodies
POST /api/employees
```json
{ "name": "Eslam", "age": 25, "salary": 7000 }
```
POST /api/employees/with-emails
```json
{
  "name": "Eslam", "age": 25, "salary": 7000,
  "emails": [
    { "name": "gmail", "content": "eslam@gmail.com" },
    { "name": "yahoo", "content": "eslam@yahoo.com" }
  ]
}
```
POST /api/emails
```json
{ "name": "gmail", "content": "eslam@gmail.com", "employeeId": 1 }
```

## Validation
- Employee: name not null/empty; 15 < age < 40; 5000 < salary < 10000
- Email: name not null/empty; content must match an email pattern
- Errors -> 400 with a field->message map; missing resources -> 404
