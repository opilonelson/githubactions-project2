## Quick run (local, for testing only)

Requirements:
- Java 8
- Gradle (or use your preferred method)

Run:
```
./gradlew bootRun
```

Endpoints:
- GET /users/search?q=admin
- GET /users/{id}
- POST /users  (JSON with id, username, password)
- DELETE /users/{id}

Reminder: This project is intentionally insecure. Use in an isolated environment only.
