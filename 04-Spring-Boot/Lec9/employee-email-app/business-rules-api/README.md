# Business Rules API

A Java 17 / Spring Boot 3 project containing ten independent, in-memory REST API exercises.  It has no database setup requirement: data lives only while the application is running.

## Run

```powershell
cd "C:\Users\mando\OneDrive\Documents\New project\business-rules-api"
mvn spring-boot:run
```

The application runs at `http://localhost:8080`. Request validation errors and business-rule failures are returned as JSON with an HTTP 400, 404, or 409 status.

## Modules and endpoints

| Task | Base path | Key operations |
| --- | --- | --- |
| Appointment booking | `/api/appointments` | Book, cancel, list, set doctor availability |
| Product discounts | `/api/discounts` | Add products, calculate a quote |
| Bank transfers | `/api/transfers` | Create accounts, transfer money |
| Library borrowing | `/api/library` | Add books/members, borrow, return |
| Table reservations | `/api/reservations` | Add tables, reserve, confirm, cancel |
| Employee leave | `/api/leaves` | Add employee, request/approve/reject/cancel leave, balance |
| E-commerce orders | `/api/orders` | Add catalog/customer, create/update/confirm order |
| Parking | `/api/parking` | Enter, exit/lost ticket, capacity |
| Course enrollment | `/api/enrollments` | Add course/student, enroll, drop |
| Delivery orders | `/api/deliveries` | Add driver, create/assign/cancel/advance delivery |

## Example: appointment

```powershell
$start = (Get-Date).AddDays(1).ToString("yyyy-MM-ddTHH:mm:ss")
Invoke-RestMethod -Method Post http://localhost:8080/api/appointments -ContentType application/json -Body "{`"doctorId`":`"dr-1`",`"patientId`":`"pt-1`",`"startsAt`":`"$start`",`"serviceType`":`"CONSULTATION`"}"
```

## Important implementation notes

- Each service synchronizes its state-changing methods, so all rule checks happen before an in-memory state update. The transfer and order flows particularly avoid partial updates on failure.
- The documented time values are ISO-8601 local date/times, for example `2026-10-08T14:30:00`.
- Sample discount categories are `BOOKS` (10%), `ELECTRONICS` (8%), and `CLOTHING` (15%). Eligible product discounts are capped at 30%; VIP adds 5%; usable codes are `WELCOME10`, `FLASH5`, and `SAVE15`.
- Parking rates and limits, free-shipping threshold, transfer limits, leave allowances, delivery limits, and restaurant hours are expressed as constants near the relevant service, making them easy to adapt.
