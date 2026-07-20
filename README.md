# SupplySync AI — Backend

A full-stack **Supply Chain & Inventory Management System** built with Spring Boot, designed to help businesses track products, manage purchase/sales orders, monitor stock levels, and generate business insights — with a built-in AI assistant for natural-language queries.

🔗 **Live API:** [supplysync-ai-backend-1.onrender.com](https://supplysync-ai-backend-1.onrender.com)
🔗 **Frontend Repo:** [SupplySync-AI-Frontend](https://github.com/harshpandey9198/SupplySync-AI-Frontend)
📄 **API Docs (Swagger):** `/swagger-ui.html`

---

## Features

- 🔐 **JWT-based authentication** with role-based access control (Admin / Manager / Staff)
- 📦 **Product & Inventory management** — track stock, reorder levels, low-stock alerts
- 🧾 **Purchase Orders** — manage supplier orders and receiving
- 🛒 **Sales Orders** — manage customer orders with automatic stock deduction
- 🚚 **Stock Movement tracking** — full audit trail of stock in/out
- 🏬 **Supplier & Warehouse management**
- 📊 **Dashboard & Reports** — real-time business metrics
- 📥 **Excel import/export** for bulk operations
- 🤖 **AI Assistant** — natural language queries over business data
- 📘 **Swagger/OpenAPI** documentation

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 3.5 |
| Security | Spring Security + JWT |
| Database | MySQL 8 |
| ORM | Spring Data JPA / Hibernate |
| Build Tool | Maven |
| API Docs | Springdoc OpenAPI (Swagger) |
| Deployment | Render |

---

## Architecture

Standard layered architecture:

```
Controller  →  Service  →  Repository  →  Entity (Database)
                  ↑
              DTOs (Request/Response)
```

- **Controllers** — expose REST endpoints, handle HTTP concerns only
- **Services** — business logic (stock deduction, validations, calculations)
- **Repositories** — Spring Data JPA interfaces for database access
- **DTOs** — decouple API contracts from internal entity structure
- **GlobalExceptionHandler** — centralized error handling → consistent error responses
- **JWT Filter** — validates tokens on every request before reaching controllers

---

## API Overview

| Module | Base Path |
|---|---|
| Auth | `/api/auth` |
| Products | `/api/products` |
| Categories | `/api/categories` |
| Suppliers | `/api/suppliers` |
| Warehouses | `/api/warehouses` |
| Inventory | `/api/inventory` |
| Purchase Orders | `/api/purchase-orders` |
| Sales Orders | `/api/sales-orders` |
| Stock Movements | `/api/stock-movements` |
| Dashboard | `/api/dashboard` |
| Reports | `/api/reports` |
| Excel Import/Export | `/api/excel` |
| AI Assistant | `/api/ai` |

Full interactive documentation available at `/swagger-ui.html` once the server is running.

---

## Getting Started

### Prerequisites

- Java 21
- MySQL 8+
- Maven (or use the included `mvnw` wrapper)

### 1. Clone the repository

```bash
git clone https://github.com/harshpandey9198/SupplySync-AI-Backend.git
cd SupplySync-AI-Backend
```

### 2. Create the database

```sql
CREATE DATABASE supplysync;
```

### 3. Configure environment variables

The app reads DB and JWT config from environment variables. Set the following before running:

| Variable | Description | Example |
|---|---|---|
| `DB_URL` | MySQL JDBC URL | `jdbc:mysql://localhost:3306/supplysync` |
| `DB_USERNAME` | MySQL username | `root` |
| `DB_PASSWORD` | MySQL password | `yourpassword` |
| `JWT_SECRET` | Secret key for signing JWTs | any long random string |

**Windows (PowerShell):**
```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/supplysync"
$env:DB_USERNAME="root"
$env:DB_PASSWORD="yourpassword"
$env:JWT_SECRET="your-random-secret-key"
```

**Mac/Linux:**
```bash
export DB_URL=jdbc:mysql://localhost:3306/supplysync
export DB_USERNAME=root
export DB_PASSWORD=yourpassword
export JWT_SECRET=your-random-secret-key
```

### 4. Run the application

```bash
./mvnw spring-boot:run       # Mac/Linux
.\mvnw.cmd spring-boot:run   # Windows
```

The server starts on `http://localhost:8080`.

On first run, sample data (products, suppliers, warehouses, sales/purchase orders) is automatically seeded, along with a default admin account:

```
Email:    admin@supplysync.com
Password: admin123
```

### 5. Explore the API

Visit `http://localhost:8080/swagger-ui.html` for interactive API documentation.

---

## Screenshots

<img width="1865" height="932" alt="Screenshot 2026-07-20 111635" src="https://github.com/user-attachments/assets/55a32cc9-ab05-4d8b-abcf-4c3667d85d57" />


---

## Future Improvements

- [ ] Pagination & filtering on list endpoints
- [ ] Request validation (`@Valid` on DTOs)
- [ ] Unit & integration test coverage
- [ ] Dockerized setup (backend + MySQL via docker-compose)
- [ ] CI/CD pipeline via GitHub Actions

---

## Author

**Harsh Pandey**
[GitHub](https://github.com/harshpandey9198)
