# URL Shortener with Rate Limiting and Analytics

A Spring Boot-powered URL shortener that supports real-time click analytics, and Redis caching.

## 🚀 Features

- Shorten long URLs
- Click analytics: IP, timestamp, device info (optional)
- Redis caching for high performance redirection
- PostgreSQL for persistent storage


## 🛠️ Tech Stack

- Java, Spring Boot
- Redis, PostgreSQL

## 📦 Installation

```bash
git clone https://github.com/yourusername/url-shortener-rate-limiter.git
cd url-shortener-rate-limiter
./mvnw spring-boot:run
```

## 📖 API (To be added)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST   | /api/v1/links/ | Shorten a URL |
| GET    | /{alias}       | Redirect to original URL |

## 🧪 Roadmap

- [x] Basic shortening + redirect
- [ ] Rate limiting using Redis
- [ ] Add admin dashboard
- [ ] Deploy with CI/CD

## 📄 License

MIT
