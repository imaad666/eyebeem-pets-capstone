# Docker, MySQL, and Backend Database Connection

This document explains how to run MySQL (including via Docker), how the database is set up, and **where the connection is configured and used** in the backend code.

---

## 1. Running MySQL with Docker

The project expects a MySQL 8 server with a database named `eyebeem` and a user the backend can use. You can run MySQL in Docker without any project-provided Dockerfile.

### One-off container (no compose file)

```bash
docker run -d \
  --name eyebeem-mysql \
  -p 3306:3306 \
  -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=eyebeem \
  -e MYSQL_USER=app \
  -e MYSQL_PASSWORD=app \
  mysql:8
```

- **`-p 3306:3306`** — Exposes MySQL on `localhost:3306` so the backend (and MySQL Workbench) can connect.
- **`MYSQL_DATABASE=eyebeem`** — Creates the `eyebeem` database.
- **`MYSQL_USER` / `MYSQL_PASSWORD`** — Creates user `app` with password `app` (what the backend uses).

Connect from MySQL Workbench (or any client) to:

- **Host:** `localhost`
- **Port:** `3306`
- **Database:** `eyebeem`
- **User:** `app`
- **Password:** `app`

### Optional: docker-compose

You can add a `docker-compose.yml` in the project root if you prefer:

```yaml
services:
  mysql:
    image: mysql:8
    container_name: eyebeem-mysql
    ports:
      - "3306:3306"
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: eyebeem
      MYSQL_USER: app
      MYSQL_PASSWORD: app
```

Then run: `docker-compose up -d`

---

## 2. Database schema and seed data

After MySQL is running and the `eyebeem` database exists:

1. **Fresh database** — Run in order in MySQL (e.g. in Workbench):
   - `backend/src/main/resources/schema.sql` — creates tables: `inventory`, `users`, `cart`, `cart_items`, `orders`, `order_items`.
   - `backend/src/main/resources/data.sql` — seeds products (e-CAT, e-DOG, e-HAMSTER) and sample users.

2. **Existing database that only needs cart tables** — Run:
   - `backend/src/main/resources/db/migration-add-cart.sql` — adds `cart` and `cart_items` only.

The backend is configured with **`spring.jpa.hibernate.ddl-auto=none`**, so it does **not** create or alter tables itself; you must run the SQL scripts above.

---

## 3. Where the connection is configured (backend)

The MySQL connection is defined by **Spring Boot configuration**. No custom `DataSource` bean is required.

### 3.1 Configuration file

**File:** `backend/src/main/resources/application.properties`

| Property | Role |
|----------|------|
| `spring.datasource.url` | JDBC URL: `jdbc:mysql://localhost:3306/eyebeem?...` — host, port, database, and options. |
| `spring.datasource.username` | MySQL user name (`app`). |
| `spring.datasource.password` | MySQL password (`app`). |
| `spring.datasource.driver-class-name` | JDBC driver: `com.mysql.cj.jdbc.Driver`. |

Spring Boot uses these to create a **DataSource** bean automatically. That DataSource is what the rest of the stack uses to talk to MySQL.

### 3.2 JPA / Hibernate

Same file:

- **`spring.jpa.hibernate.ddl-auto=none`** — Hibernate does not create/update schema; we rely on the SQL scripts.
- **`spring.jpa.show-sql`** / **`spring.jpa.properties.hibernate.format_sql`** — Optional logging.

**`spring-boot-starter-data-jpa`** (in `backend/pom.xml`) configures Hibernate to use the DataSource above. So the **actual connection to MySQL** is established by:

1. Reading `application.properties`.
2. Creating a DataSource (connection pool) to `jdbc:mysql://localhost:3306/eyebeem`.
3. Passing that DataSource to Hibernate’s `EntityManagerFactory` and thus to every JPA operation.

There is no explicit “connection” code in the project; it’s all done by Spring Boot auto-configuration based on those properties.

---

## 4. Where the connection is used in code (backend)

The connection is never opened manually. It is used indirectly:

1. **Repositories** — Each `*Repository` interface extends `JpaRepository`. When you call a method (e.g. `findByEmail`, `findAll`), Spring Data JPA uses the DataSource (and thus the MySQL connection) to run SQL.

   **Location:** `backend/src/main/java/com/eyebeem/backend/repository/`
   - `UserRepository.java` — e.g. `findByEmail(String email)`.
   - `ProductRepository.java` — products from `inventory`.
   - `CartRepository.java`, `OrderRepository.java` — cart and orders.

2. **Services** — Services inject these repositories and call their methods. Every such call triggers database access over the same connection pool.

   **Location:** `backend/src/main/java/com/eyebeem/backend/service/`
   - `AuthService.java` — uses `UserRepository` (login/register).
   - `UserService.java` — user and order history.
   - `ProductService.java` — product listing.
   - `CartService.java` — get/add/remove cart, clear cart.
   - `OrderService.java` — place order, order history.

   Methods are annotated with `@Transactional` (read-only or read-write). Transactions use the same DataSource/connection.

3. **Controllers** — Controllers call services; they do not touch the database directly.

   **Location:** `backend/src/main/java/com/eyebeem/backend/controller/`

So the flow is:

```
application.properties (url, username, password)
    → Spring Boot DataSource (connection pool to MySQL)
        → JPA / Hibernate (EntityManagerFactory using that DataSource)
            → Repositories (JpaRepository methods run SQL)
                → Services (call repositories)
                    → Controllers (call services)
```

---

## 5. Summary

| What | Where |
|------|--------|
| **Docker** | Run MySQL with `docker run` (or optional `docker-compose`) on port **3306**; create DB `eyebeem` and user `app`/`app`. |
| **Connection config** | **`backend/src/main/resources/application.properties`** — `spring.datasource.url`, `username`, `password`, `driver-class-name`. |
| **Who uses the connection** | Spring Boot creates a **DataSource** from those properties; **JPA/Hibernate** and all **repositories** in `backend/.../repository/` use it; **services** in `backend/.../service/` use the repositories. |
| **Schema/seed** | **`backend/src/main/resources/schema.sql`** and **`data.sql`**; optional **`db/migration-add-cart.sql`** if you only add cart tables. |

For running the backend and calling the API, see the main [README.md](README.md) and [backend/README.md](backend/README.md).
