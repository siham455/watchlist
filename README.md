
# Watchlist Application

The **Watchlist Application** is a Spring Boot-based REST API designed to help users manage their watchlists for movies and TV shows. Users can add, update, delete, and retrieve watchlist titles, as well as filter them by genre, type, binge-worthiness, and IMDb rating.

---

## Features

- **User Management**:
  - Create, update, delete, and retrieve users.
  - Each user can have their own watchlist.

- **Watchlist Management**:
  - Add, update, delete, and retrieve watchlist titles (movies or TV shows).
  - Filter watchlist titles by:
    - Genre
    - Type (Movie or TV Show)
    - Binge-worthiness
    - IMDb rating (high-value titles)
  - Validate watchlist titles (e.g., ensure required fields are provided).

- **Advanced Filtering**:
  - Retrieve watchlist titles by user, genre, type, or binge-worthiness.
  - Find high-value titles (above-average IMDb rating).

- **User Tests**:
  - Comprehensive unit and integration tests for user-related functionality.
  - Tests cover:
    - Creating a new user.
    - Retrieving a user by ID.
    - Updating user details.
    - Deleting a user.
    - Handling invalid user requests (e.g., non-existent user ID).
  - Built using **JUnit** and **Mockito** for reliable and maintainable test cases.

---

## Technologies Used

- **Backend**: Spring Boot, Java
- **Database**: MySQL
- **API Documentation**: Spring Web (REST API)
- **Testing**: JUnit, Mockito (for unit and integration testing)

---

## API Endpoints

### User Endpoints

| Method | Endpoint               | Description                          |
|--------|------------------------|--------------------------------------|
| GET    | `/api/users`           | Get all users.                       |
| GET    | `/api/users/{id}`      | Get a user by ID.                    |
| POST   | `/api/users`           | Create a new user.                   |
| PUT    | `/api/users/{id}`      | Update a user by ID.                 |
| DELETE | `/api/users/{id}`      | Delete a user by ID.                 |

### Watchlist Endpoints

| Method | Endpoint                                      | Description                          |
|--------|-----------------------------------------------|--------------------------------------|
| GET    | `/api/users/{userId}/watchlist`               | Get all watchlist titles for a user. |
| GET    | `/api/users/{userId}/watchlist/{id}`          | Get a specific watchlist title by ID.|
| POST   | `/api/users/{userId}/watchlist`               | Add a new watchlist title.           |
| PUT    | `/api/users/{userId}/watchlist/{id}`          | Update a watchlist title by ID.      |
| DELETE | `/api/users/{userId}/watchlist/{id}`          | Delete a watchlist title by ID.      |
| GET    | `/api/users/{userId}/watchlist/high-value`    | Get high-value titles (IMDb ≥ avg).  |
| GET    | `/api/users/{userId}/watchlist/genre/{genre}` | Get watchlist titles by genre.       |
| GET    | `/api/users/{userId}/watchlist/type/{type}`   | Get watchlist titles by type.        |
| GET    | `/api/users/{userId}/watchlist/binge-worthy/{bingeWorthy}` | Get binge-worthy titles. |

---

## Database Schema

### User Table
| Column      | Type   | Description                |
|-------------|--------|----------------------------|
| `id`        | UUID   | Unique identifier.         |
| `firstName` | String | User's first name.         |
| `lastName`  | String | User's last name.          |

### Watchlist Table
| Column            | Type    | Description                          |
|-------------------|---------|--------------------------------------|
| `id`              | UUID    | Unique identifier.                   |
| `title`           | String  | Title of the movie/TV show.          |
| `type`            | String  | Type (`MOVIE` or `TV_SHOW`).         |
| `averageDuration` | int     | Average duration in minutes.         |
| `seasons`         | Integer | Number of seasons (for TV shows).    |
| `episodes`        | Integer | Number of episodes (for TV shows).   |
| `bingeWorthy`     | boolean | Whether the title is binge-worthy.   |
| `imdbRating`      | double  | IMDb rating (0-10).                  |
| `genre`           | String  | Genre of the title.                  |
| `dateTime`        | Instant | Timestamp of when the title was added.|
| `user_id`         | UUID    | Foreign key to the `User` table.     |

---

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven
- MySQL (or any other relational database supported by JPA)

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/siham455/watchlist.git
   cd watchlist
   ```

2. Configure the database:
   - Update `application.properties` with your database credentials:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:3306/watchlist
     spring.datasource.username="username"
     spring.datasource.password="password"
     spring.jpa.hibernate.ddl-auto=update
     ```

3. Build and run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

4. Access the API at `http://localhost:8080`.

---

## Testing

The project includes unit and integration tests using JUnit and Mockito. To run the tests:

```bash
./mvnw test
```
---

## SQL Dump File

[Uploading watchlist_users.sql…]()-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: watchlist
-- ------------------------------------------------------
-- Server version	8.4.3

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` binary(16) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (_binary '�\�{�;E����ٺ�V#','Siham','Ali');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-02 23:59:01

MY DUMP FILES DO NOT WANT TO ATTACH TO THIS READ ME, CONTATCT ME SO I CAN SEND IT.
