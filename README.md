# PageTurner Bookstore — Backend (Spring Boot)

Production-ready backend API service for the PageTurner Online Bookstore application, built with **Java 17+, Spring Boot 3.3.5, Spring Data JPA, and H2 Database**.

---

## 🛠️ Features & Architecture
- **RESTful Endpoints**: Complete CRUD for Books and checkout processing for Customer Orders.
- **Stock Integrity**: Real-time inventory verification and automatic quantity deduction upon order creation.
- **H2 Embedded Database**: Auto-seeded on first launch; console available for debugging.
- **Global CORS Config**: Pre-configured cross-origin sharing for decoupled frontend deployments.
- **Container Ready**: Multi-stage `Dockerfile` and `docker-compose.yml` included.
- **Cloud Compatible**: Dynamic `$PORT` binding for instant deployment on Render, Railway, or Heroku.

---

## 🚀 Running Locally

```bash
# Using Maven Wrapper (Windows)
.\mvnw.cmd spring-boot:run

# Using Maven Wrapper (Linux / macOS)
chmod +x ./mvnw
./mvnw spring-boot:run
```

App will start at **http://localhost:8080**.
H2 Database Console: **http://localhost:8080/h2-console** (`jdbc:h2:file:./data/bookstoredb`).

---

## 🐳 Docker Deployment

```bash
docker build -t pageturner-backend .
docker run -p 8080:8080 pageturner-backend
```

---

## 📡 API Specification

| HTTP Method | Route | Description |
|---|---|---|
| `GET` | `/api/books` | Get all books (supports optional `?q=` search query) |
| `GET` | `/api/books/{id}` | Get book by ID |
| `POST` | `/api/books` | Add new book |
| `PUT` | `/api/books/{id}` | Update existing book |
| `DELETE` | `/api/books/{id}` | Delete book |
| `POST` | `/api/orders` | Place customer order |
| `GET` | `/api/orders` | List all placed orders |
| `GET` | `/api/orders/{id}` | Get single order details |
