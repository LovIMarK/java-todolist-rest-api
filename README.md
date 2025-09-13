# Java Todolist REST API

![Java](https://img.shields.io/badge/language-Java-blue)
![Docker](https://img.shields.io/badge/dockerized-yes-brightgreen)
![API REST](https://img.shields.io/badge/api-rest-red)
![Traefik](https://img.shields.io/badge/proxy-traefik-purple)
![Status](https://img.shields.io/badge/project-stable-success)

This project demonstrates a complete RESTful API developed in Java using the Javalin framework.  
It includes a static frontend, a backend API, and a complete containerized infrastructure using Docker and Traefik.  
The system supports HTTPS, load balancing with replicas, sticky sessions, and frontend integration using AJAX.

---

## Table of Contents

1. Project Overview
2. Technologies Used
3. How to Run
4. API Features
5. Testing with Insomnia
6. Frontend Integration
7. Load Balancing and Scaling
8. HTTPS Configuration
9. Documentation
10. Author
11. License

---

## 1. Project Overview

- Static website served with Nginx and Docker
- Java REST API with CRUD operations for task management
- Docker Compose orchestration
- Traefik reverse proxy with route management and HTTPS
- Load balancing and sticky sessions
- Frontend connected to the API via JavaScript (AJAX)
- Infrastructure ready for scaling and deployment

---

## 2. Technologies Used

- Java (Javalin)
- Maven
- Docker & Docker Compose
- Traefik
- HTML / CSS / JavaScript
- Insomnia (API testing)

---

## 3. How to Run

1. Clone the repository:
```bash
git clone https://github.com/LovIMarK/java-todolist-rest-api.git
cd java-todolist-rest-api
```

2. Build and start the services:
```bash
docker compose build
docker compose up -d
```

3. Access the application:

- Static site: http://static-web.localhost
- REST API: http://todolist-api.localhost/tasks
- Traefik Dashboard: http://localhost:8080

---

## 4. API Features

The REST API supports standard CRUD operations:

- `POST /tasks` — Create a new task
- `GET /tasks` — Get all tasks
- `GET /tasks/{id}` — Get a task by ID
- `PUT /tasks/{id}` — Update a task
- `DELETE /tasks/{id}` — Delete a task

Each endpoint was tested using Insomnia with expected HTTP status codes.

---

## 5. Testing with Insomnia

The following scenarios were tested:

- Create task (`POST /tasks`) → 201 Created
- Get all tasks (`GET /tasks`) → 200 OK
- Get by ID (`GET /tasks/{id}`) → 200 OK or 404 Not Found
- Update task (`PUT /tasks/{id}`) → 200 OK
- Delete task (`DELETE /tasks/{id}`) → 204 No Content

Screenshots are available in `Rapport/images/`.

---

## 6. Frontend Integration

AJAX was used to connect the frontend with the API:

- A "Task Manager" section was added to `index.html`
- JavaScript handles API calls using `fetch()`
- Users can create, update, and delete tasks directly from the web UI
- CORS configuration was handled via Traefik labels

---

## 7. Load Balancing and Scaling

Multiple instances of the API and static frontend can be deployed:

```bash
docker compose up -d --scale static-web=3 --scale todolist-api=3
```

Traefik balances requests between instances using round-robin, and sticky sessions are enabled for the API.

---

## 8. HTTPS Configuration

A self-signed certificate is used for HTTPS. It was generated using OpenSSL:

```bash
openssl req -newkey rsa:4096 -nodes -keyout ./certificates/key.key -x509 -out ./certificates/certificat.crt -days 365
```

Traefik is configured to load the certificate via volume mounts and route HTTPS traffic accordingly.

---

## 9. Documentation

A detailed report of the project (in French) is available here:  
[View RAPPORT_FR.md](./RAPPORT_FR.md)

---

## 10. Author

Developed as part of a REST API and infrastructure course.  
Grade received: 5.5/6  
HEIG-VD, 2025

---

## 11. License

This project is licensed under the MIT License.
