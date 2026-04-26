Here’s a polished version of your README that includes the **React + Vite frontend** alongside your Spring Boot microservices:

---

# Recipe Book Microservices

This project is a full‑stack application combining **Spring Boot microservices** with a **React + Vite frontend**. It manages recipes, ingredients, and scaling logic, with a clean separation of concerns between backend services and the UI.

---

## Project Overview

The system consists of three primary components:

* **Recipe Service (Backend)**: Manages core recipe data, including ingredients and instructions. Maintains a master list of ingredients for consistency.
* **Scaling Service (Backend)**: Dedicated service to handle scaling ingredient quantities based on portion sizes.
* **Frontend (React + Vite)**: A Material‑UI powered interface that allows users to browse recipes, search by ingredient, create new recipes, and view scaled portions.

---

## Technical Stack

* **Java**: 21
* **Framework**: Spring Boot 4.0.6‑SNAPSHOT
* **Build Tool**: Maven with Wrapper
* **Database**: H2 In‑Memory Database (development) / MySQL (optional)
* **Communication**: Spring Cloud OpenFeign
* **Documentation**: SpringDoc OpenAPI (Swagger UI)
* **Utilities**: Project Lombok
* **Frontend**: React 18 + Vite, Axios, Material‑UI, React Router

---

## Features

### Backend
* **Create Recipes**: Add new recipes with detailed instructions and ingredient quantities.
* **Master Ingredient Sync**: Tracks unique ingredients in a `master_ingredients` table.
* **Automated Seeding**: Seeds an initial "Ginger Chai" recipe on startup.
* **REST Endpoints**:
    - `POST /api/recipes/create`
    - `GET /api/recipes/getall`
    - `GET /api/recipes/{id}`
    - `DELETE /api/recipes/{id}`
    - `GET /api/recipes/ingredient/{name}/recipes`
    - `GET /api/recipes/masteringredients`


#### Entity relationship diagram
```mermaid
erDiagram
    Recipe {
        bigint id PK
        int servings
        string name
        string instructions
    }

    Ingredient {
        bigint id PK
        double quantity
        string unit
        bigint recipe_id FK
        bigint masterIngredient_id FK
    }

    MasterIngredient {
        bigint id PK
        string name UNIQUE
    }

    Recipe ||--o{ Ingredient : contains
    MasterIngredient ||--o{ Ingredient : referenced_by

```

### Frontend
* **Browse Recipes**: View all recipes or search by ID.
* **Ingredient Browser**: Displays master ingredients in a 5‑column grid; click to view recipes using that ingredient.
* **Create Recipes**: Dynamic form with ingredient entry.
* **Delete Recipes**: Remove recipes by ID.
* **UI**: Built with Material‑UI for a polished, responsive interface.

---

## Configuration

### Recipe Service (`8081`)
* Port: 8081
* H2 Console: `/h2-console`
* Swagger UI: `/swagger-ui.html`

### Scaling Service (`8082`)
* Port: 8082

### Frontend
* Runs locally with Vite dev server (`5173` by default).
* Environment variables (`.env`):
  ```env
  VITE_RECIPE_SERVICE=http://localhost:8081/api/
  VITE_SCALING_SERVICE=http://localhost:8082/api/
  ```

---

## Getting Started

### Prerequisites
* JDK 21
* Node.js 18+
* Maven Wrapper

### Backend
```bash
# Build
./mvnw clean install

# Run Recipe Service
cd recipe-service
./mvnw spring-boot:run

# Run Scaling Service
cd scaling-service
./mvnw spring-boot:run
```

### Frontend
```bash
# Setup
cd frontend
npm install

# Run locally
npm run dev
```

Access frontend at: `http://localhost:5173`

---

## Verify
* Backend: Swagger UI at `http://localhost:8081/swagger-ui.html`
* Frontend: Navigate to `http://localhost:5173` and browse recipes.

---