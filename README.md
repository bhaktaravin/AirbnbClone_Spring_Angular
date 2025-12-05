# Airbnb Clone - Spring Boot REST API

A RESTful API built with Spring Boot and MongoDB for managing Airbnb-style property listings.

## Features

- ✅ Full CRUD operations for listings
- ✅ Pagination and sorting support
- ✅ Filter by property type and room type
- ✅ MongoDB integration with complex nested documents
- ✅ Lombok for reduced boilerplate code

## Tech Stack

- **Spring Boot 4.0.0**
- **MongoDB** (MongoDB Atlas)
- **Lombok**
- **Spring Data MongoDB**
- **Maven**

## Prerequisites

- Java 17 or higher
- Maven
- MongoDB Atlas account (or local MongoDB instance)

## Setup

### 1. Clone the repository

```bash
git clone <your-repo-url>
cd projects
```

### 2. Configure MongoDB Connection

Create a file `src/main/resources/application-local.properties` with your MongoDB credentials:

```properties
spring.data.mongodb.uri=mongodb+srv://<username>:<password>@<cluster>.mongodb.net/sample_airbnb
spring.data.mongodb.database=sample_airbnb
```

**Important:** Never commit actual credentials to Git. The `application-local.properties` file is already in `.gitignore`.

### 3. Run the application

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

Or on Windows:

```bash
mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=local
```

The API will start on `http://localhost:8080`

## API Endpoints

### Listings

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/listings` | Get all listings (paginated) |
| GET | `/api/listings/{id}` | Get listing by ID |
| POST | `/api/listings` | Create new listing |
| PUT | `/api/listings/{id}` | Update entire listing |
| PATCH | `/api/listings/{id}` | Partial update listing |
| DELETE | `/api/listings/{id}` | Delete listing |
| GET | `/api/listings/property-type/{type}` | Filter by property type |
| GET | `/api/listings/room-type/{type}` | Filter by room type |

### Pagination Parameters

- `page` - Page number (default: 0)
- `size` - Items per page (default: 10)
- `sortBy` - Field to sort by (default: "id")
- `sortDirection` - "asc" or "desc" (default: "asc")

### Example Requests

```bash
# Get first page with 20 items, sorted by price descending
GET /api/listings?page=0&size=20&sortBy=price&sortDirection=desc

# Get listing by ID
GET /api/listings/10059872

# Filter by property type
GET /api/listings/property-type/Apartment?page=0&size=10
```

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── sample_airbnb/
│   │       └── projects/
│   │           ├── ProjectsApplication.java
│   │           ├── controller/
│   │           │   └── ListingsController.java
│   │           ├── model/
│   │           │   └── Listings.java
│   │           └── repositories/
│   │               └── ListingsRepository.java
│   └── resources/
│       ├── application.properties
│       └── application-local.properties (not committed)
```

## Data Model

The `Listings` model includes:
- Basic listing information (name, summary, description)
- Pricing details (price, weekly_price, monthly_price)
- Property details (property_type, room_type, bed_type)
- Nested objects:
  - `Images` - Property images
  - `Host` - Host information
  - `Address` - Location with GeoJSON coordinates
  - `Availability` - Calendar availability
  - `ReviewScores` - Review ratings
  - `Review[]` - List of reviews

## Environment Variables

For production deployment, set these environment variables:

```bash
MONGODB_URI=mongodb+srv://<username>:<password>@<cluster>.mongodb.net/sample_airbnb
MONGODB_DATABASE=sample_airbnb
```

## License

MIT
