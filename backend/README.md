# EYE BEE M Pets – Backend

Spring Boot API for products, users, cart, and orders. Uses MySQL (Docker).

## Prerequisites

- MySQL running (e.g. Docker: `eyebeem-mysql` on port 3306, database `eyebeem`, user `app` / password `app`).


## Run

```bash
cd backend
mvn spring-boot:run
```

API base: `http://localhost:8081`

## API summary

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/users/login` | Login/register; body `{ "name", "email" }`; returns user + order history |
| GET | `/api/users/{userId}` | User profile + order history |
| GET | `/api/products` | List all products (with quantity) |
| GET | `/api/products/{productId}` | One product by productId (1, 2, 3) |
| GET | `/api/cart?userId=` | Get cart for user |
| POST | `/api/cart/items` | Add to cart; body `{ "userId", "productId", "quantity" }` |
| DELETE | `/api/cart/items/{cartItemId}?userId=` | Remove line from cart |
| DELETE | `/api/cart?userId=` | Clear cart |
| POST | `/api/orders?userId=` | Place order (uses cart, reduces inventory, clears cart) |
| GET | `/api/orders/history?userId=` | Order history for user |
