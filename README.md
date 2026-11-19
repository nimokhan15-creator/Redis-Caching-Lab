
# Redis Caching Lab

## Project Overview
This project demonstrates Redis caching with PostgreSQL using Java and Jedis. It implements cache-aside caching, measures cache performance, handles stale data, and demonstrates application behavior when Redis is unavailable.

## Technologies Used
- Java 17
- Maven
- Redis 7
- PostgreSQL 16
- Jedis 5.2.0
- Docker Compose
- IntelliJ IDEA

## Project Features

### Task 1: Redis Basics
Demonstrates Redis SET, GET, INCR commands and key expiration using TTL.

### Task 2: Cache-Aside Pattern
The application checks Redis first. If the product is not cached, it retrieves the product from PostgreSQL and stores it in Redis for 300 seconds.

### Task 3: Cache Hit Ratio
Executes 1,000 requests using random product IDs between 1 and 100 and measures Redis cache hits and misses.

### Task 4: Stale Data and Cache Invalidation
Demonstrates stale data when the database is updated without invalidating Redis, then fixes it by deleting the cached key after updating the database.

### Task 5: Redis Failure Handling
Demonstrates that the application can retrieve products from PostgreSQL when Redis is unavailable.

## How to Run

1. Start Docker Desktop.
2. Start the containers:

   ```bash
   docker compose up -d
   ```

3. Open the project in IntelliJ IDEA.
4. Run `Main.java`.

## Database Configuration
- Database: shop
- Username: app
- Password: apppass
- PostgreSQL port: 5434
- Redis port: 6380

## Author
Nimra
