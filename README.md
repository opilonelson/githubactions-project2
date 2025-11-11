# Vulnerable Spring Boot Demo (for educational use only)

**Warning:** This project is intentionally vulnerable and contains insecure code and outdated dependencies.
Use only in isolated test environments (offline / firewalled) and never on production or public networks.

Contents:
- Minimal CRUD user API with unsafe SQL concatenation (SQL injection risk).
- Insecure security configuration (CSRF disabled, NoOpPasswordEncoder).
- Old Spring Boot version in `build.gradle`.
- GitHub Actions workflow that runs OWASP Dependency-Check.

Purpose: Security training, scanners testing, and learning how to find and fix vulnerabilities.
