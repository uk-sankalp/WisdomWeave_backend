# WisdomWeave – Blog Backend

Production-style Spring Boot backend powering the WisdomWeave blogging platform.

Designed with secure authentication, scalable architecture, and REST-first design principles.  
This backend serves as the core API layer for a modern blog system.

**Owner:** Oggy aka Sankalp

---

## Overview

WisdomWeave backend is a full-featured REST API built with Spring Boot.  
It supports authentication, role-based authorization, posts, comments, likes, user profiles, avatar uploads, and an admin dashboard.

The architecture follows clean layered separation:

Controller → Service → Repository → Database

It is structured to scale into a real production environment.

---

## Tech Stack

- Java 21
- Spring Boot
- Spring Security
- JWT Authentication
- Hibernate / JPA
- PostgreSQL
- Multipart file storage
- REST API architecture
- Maven build system

---

## Core Features

### Authentication & Security

- User registration and login
- JWT-based authentication
- Role-based authorization (USER / ADMIN)
- Protected routes
- Secure password hashing
- Stateless session handling

---

### User System

- Profile management
- Update username & bio
- Avatar upload & deletion
- File serving for profile images

---

### Blog Posts

- Create posts (admin-only)
- Paginated post listing
- Search functionality
- Fetch single post
- Clean DTO-based responses

---

### Comments

- Add comments (authenticated users)
- Fetch post comments
- Linked user attribution

---

### Likes System

- Like / unlike posts
- Real-time like count
- Prevent duplicate likes per user

---

### Admin Dashboard

- Platform analytics endpoint
- Total users
- Total posts
- Total likes
- Admin-only access

---

### File Handling

- Avatar image upload
- UUID-based file naming
- Secure file serving endpoint
- Local storage abstraction

---

## API Design Principles

- RESTful endpoints
- Stateless authentication
- DTO separation
- Clean error handling
- Validation annotations
- Consistent response format

---

## Project Structure

```
controller/   → REST endpoints
service/      → business logic
repository/   → database access
entity/       → JPA models
dto/          → request/response objects
security/     → JWT & filters
config/       → app configuration
storage/      → file upload handling
```

---

## Setup

### 1. Clone repo

```bash
git clone <repo-url>
cd wisdomweave-backend
```

### 2. Configure database

Edit `application.properties`:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/blogdb
spring.datasource.username=your_user
spring.datasource.password=your_password
```

---

### 3. Run application

```bash
mvn spring-boot:run
```

Backend runs at:

```
http://localhost:8080
```

---

## Deployment

This backend can be deployed on:

- Render
- Railway
- Fly.io
- AWS EC2
- DigitalOcean
- Any JVM-compatible cloud

Ensure environment variables are used for secrets in production.

---

## Security Notes

- JWT secret must be stored in environment variables
- Never commit credentials
- Use HTTPS in production
- Configure CORS properly

---

## Purpose of This Project

This backend demonstrates:

- Real-world Spring Boot architecture
- Secure authentication design
- Role-based access control
- File upload handling
- Production-ready API patterns
- Clean layered backend structure

---

## Author

**Sankalp**  
Unemployed Dev.
