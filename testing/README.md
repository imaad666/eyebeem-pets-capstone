# EYE BEE M Pets - Testing

UI (Selenium), API, and DB tests organized by functional module.

## Test Suites (Modules)

| Suite | File | Description |
|-------|------|-------------|
| **Registration** | `suites/testng-registration.xml` | Login, sign up, create account, users API & DB |
| **Catalog** | `suites/testng-catalog.xml` | Products, inventory, home page, product images |
| **Cart** | `suites/testng-cart.xml` | Cart page, cart API |
| **Checkout** | `suites/testng-checkout.xml` | Checkout flow (address, payment, order) |
| **History** | `suites/testng-history.xml` | Orders page, orders API, orders DB |

## Run Tests

**Full suite (default):**
```bash
mvn test
```

**Run a single module:**
```bash
mvn clean test -DsuiteXmlFile=suites/testng-registration.xml
mvn clean test -DsuiteXmlFile=suites/testng-catalog.xml
mvn clean test -DsuiteXmlFile=suites/testng-cart.xml
mvn clean test -DsuiteXmlFile=suites/testng-checkout.xml
mvn clean test -DsuiteXmlFile=suites/testng-history.xml
```

## Allure Reports

1. Run tests (Allure results go to `target/allure-results`):
   ```bash
   mvn clean test -DsuiteXmlFile=suites/testng-registration.xml
   ```

2. Generate report:
   ```bash
   mvn allure:report
   ```

3. Open report (HTML in `target/site/allure-maven-plugin/index.html`):
   ```bash
   open target/site/allure-maven-plugin/index.html
   ```

Or use `allure:serve` to generate and open in browser:
```bash
mvn allure:serve -DsuiteXmlFile=suites/testng-registration.xml
```

## Prerequisites

- **Frontend** running at `http://localhost:5173` (for UI tests)
- **Backend** running at `http://localhost:8081` (for API tests)
- **MySQL** on `localhost:3306`, database `eyebeem`, user `app`/`app` (for DB tests)
