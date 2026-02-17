# 🚀 EYE BEE M Pets - Capstone Project Plan

**Project Type:** End-to-End Quality Engineering Implementation
**Stack:** React (Frontend) | Java Spring Boot (Backend) | MySQL (DB) | Selenium/Java (Automation)
**Theme:** Retro Monochrome "Pixel Art" E-Commerce Store for Robotic Pets

---

## 📅 Phase 0: Setup & Infrastructure
**Goal:** Initialize the development and testing environments.

- [ ] **Repository Setup**
    - [ ] Create GitHub Repo: `eye-bee-m-pets`
    - [ ] Initialize `README.md` with project description.
    - [ ] Create `.gitignore` (Java, Node, Maven).
- [ ] **Project Structure**
    - [ ] `/frontend`: React (Vite) project.
    - [ ] `/backend`: Spring Boot (Maven) project.
    - [ ] `/testing`: Selenium automation framework.
    - [ ] `/docs`: Test plans, cases, and reports.
- [ ] **Database Setup**
    - [ ] Install MySQL Server.
    - [ ] Create Database: `ebm_pets_db`.
    - [ ] Create User: `ebm_user` / `password`.

---

## 🧠 Phase 1: Analysis & Planning (Milestone 1)
[cite_start]**Goal:** Define what to build and how to test it [cite: 198-201].

### 1.1 Requirements Analysis
- [ ] **Functional Requirements (FR)**
    - [ ] **FR-01 (Auth):** User registration, login, logout.
    - [ ] **FR-02 (Catalog):** Browse pets, filter by type (e-Cat, e-Dog), sort by price.
    - [ ] **FR-03 (Cart):** Add to cart, update quantity, remove item, persistent cart.
    - [ ] **FR-04 (Checkout):** Shipping form, mock payment, order creation.
    - [ ] **FR-05 (History):** View past orders and status.
- [ ] **Non-Functional Requirements (NFR)**
    - [ ] **NFR-01:** UI/DB data consistency (Inventory updates).
    - [ ] **NFR-02:** Cross-browser compatibility (Chrome).
    - [ ] **NFR-03:** Response time < 2 seconds for catalog load.

### 1.2 Test Strategy
- [ ] **Manual Testing:** Exploratory, UX, Negative testing.
- [ ] **Automation:** Regression suite (Java/Selenium), Smoke tests.
- [ ] **Tools:** Jira (Defect tracking), Excel (Test Cases).

---

## 🎨 Phase 2: Design (Milestone 2)
[cite_start]**Goal:** Define the look, data structure, and test scenarios [cite: 251-253].

- [ ] **UI/UX Design (Retro Style)**
    - [ ] **Style Guide:** Monochrome (B&W), Pixel Fonts, 1-bit icons.
    - [ ] **Mockups:** Landing Page, Product Detail, Cart, Checkout.
- [ ] **Database Schema (MySQL)**
    - [ ] Table `users`: (id, email, password_hash, address).
    - [ ] Table `products`: (id, name, type, price, stock, image_url).
    - [ ] Table `orders`: (id, user_id, total, status, created_at).
    - [ ] Table `order_items`: (order_id, product_id, quantity).
- [ ] **Test Design**
    - [ ] Create **Test Scenarios** (High Level).
    - [ ] [cite_start]Write **Test Cases** (Detailed steps, expected results)[cite: 337].
    - [ ] [cite_start]Prepare **Test Data** (Valid users, invalid credit cards)[cite: 339].

---

## 💻 Phase 3: Development (SDLC Implementation)
**Goal:** Build the Application Under Test (AUT).

### 3.1 Backend (Spring Boot)
- [ ] **Setup:** Spring Initializr (Web, JPA, MySQL Driver, Lombok).
- [ ] **Entities:** Create Java classes for `User`, `Product`, `Order`.
- [ ] **API Endpoints:**
    - [ ] `GET /api/products`
    - [ ] `POST /api/cart/add`
    - [ ] `POST /api/checkout`
- [ ] **Seeding:** Create `data.sql` to populate initial "e-Pets".

### 3.2 Frontend (React)
- [ ] **Setup:** `npm create vite@latest frontend -- --template react`.
- [ ] **Styling:** Implement global CSS for "Retro/Pixel" look.
- [ ] **Components:**
    - [ ] `Header.jsx`: Logo and Nav.
    - [ ] `ProductCard.jsx`: Pixel art image + "Buy" button.
    - [ ] `Cart.jsx`: List items + Total calculation.
- [ ] **Integration:** Connect React to Spring Boot API (`axios` or `fetch`).
- [ ] **Testability:** Add `data-testid="XYZ"` to all interactive elements.

---

## 🧪 Phase 4: Testing & Execution (STLC Core)
[cite_start]**Goal:** Execute tests, log bugs, and automate regression [cite: 344-346, 447].

### 4.1 Manual Execution (Milestone 3)
- [ ] Execute Test Cases on the built app.
- [ ] [cite_start]Log **Defects** (Bug Reports) with Severity/Priority [cite: 371-389].
- [ ] Perform **Retesting** after fixes.

### 4.2 Database Validation
- [ ] [cite_start]Write SQL queries to verify backend data integrity[cite: 30, 167].
    - [ ] Check inventory deduction after purchase.
    - [ ] Verify user registration data.

### 4.3 Automation Framework (Milestone 4 & 5)
- [ ] [cite_start]**Setup:** Maven project with Selenium & TestNG dependencies[cite: 479].
- [ ] [cite_start]**Architecture:** Implement **Page Object Model (POM)**[cite: 484].
    - [ ] `BasePage.java` (Driver wrapper).
    - [ ] `LoginPage.java`, `CatalogPage.java`, `CartPage.java`.
- [ ] **Utilities:** `ScreenshotUtil`, `ExcelReader` (for Data-Driven tests).
- [ ] **Scripts:**
    - [ ] `Test_Registration_Flow`
    - [ ] [cite_start]`Test_EndToEnd_Purchase`[cite: 533].
- [ ] **Execution:** Run via `testng.xml` and generate reports.

---

## 📝 Phase 5: Documentation & Submission
**Goal:** Finalize deliverables for the Capstone assessment.

- [ ] [cite_start]**Test Strategy Document** (Word/PDF)[cite: 235].
- [ ] [cite_start]**Test Case Repository** (Excel)[cite: 337].
- [ ] [cite_start]**Defect Report** (Export from Jira/Excel)[cite: 438].
- [ ] **Automation Code** (Zip/GitHub Link).
- [ ] **Execution Video** (Screen recording of Automation running).
- [ ] **Final Presentation** (PowerPoint summary of work).

---

## ✅ Final Checklist
- [ ] Does the app look "Retro"?
- [ ] Are all Critical FRs working?
- [ ] Is the Automation Framework robust (POM implemented)?
- [ ] Are all citations and documents aligned with the Capstone PDF?
