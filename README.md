# URL Shortener with Rate Limiting and Analytics

A Spring Boot-powered URL shortener that supports rate-limited API access, JWT-based authentication, real-time click analytics, and Redis caching.

## 🚀 Features

- Shorten long URLs with optional custom alias
- API Rate Limiting (Redis-backed)
- JWT-based Authentication
- Click analytics: IP, timestamp, device info (optional)
- Redis caching for high performance redirection
- PostgreSQL for persistent storage
- Dockerized Microservice

## 🛠️ Tech Stack

- Java, Spring Boot
- Redis, PostgreSQL
- Spring Security (JWT)
- Docker, Swagger UI

## 📦 Installation

```bash
git clone https://github.com/yourusername/url-shortener-rate-limiter.git
cd url-shortener-rate-limiter
./mvnw spring-boot:run
```

## 📖 API (To be added)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST   | /api/shorten | Shorten a URL |
| GET    | /r/{alias}   | Redirect to original URL |
| GET    | /api/stats   | View analytics (authenticated) |

## 🧪 Roadmap

- [x] Basic shortening + redirect
- [x] Rate limiting using Redis
- [ ] Add admin dashboard
- [ ] Deploy with CI/CD

## 📄 License

MIT
