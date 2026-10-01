# Social Media REST API — Spring Boot + JWT + MySQL

Instagram-style backend: JWT auth, users (follow/unfollow/search), posts (like/unlike/save), comments, reels, stories. Layered Spring Boot with global exception handling and JPA persistence.

**Repo:** `github.com/shezad-linux/socialMedia` (code lives in `instagram/` subfolder)

## Stack

- Java 17, Spring Boot 3.2.1 (parent), spring-boot-starter-web / security / data-jpa 3.1.4 / validation, devtools
- Auth: Spring Security + `io.jsonwebtoken jjwt 0.11.1` (api/impl/jackson), `JwtGenratorFilter` + `JwtValidationFilter`, `JwtTokenProvider`, header `Authorization: Bearer <token>`
- DB: MySQL 8.0.33 (`mysql-connector-java`), Spring Data JPA/Hibernate, `ddl-auto=update`
- Tests: spring-boot-starter-test + spring-security-test
- Build: Maven wrapper (`mvnw`)

## Structure

```
instagram/src/main/java/com/social/
  InstagramApplication.java
  config/ AppConfig, JwtGenratorFilter, JwtValidationFilter, SecurityContest
  controller/ AuthController, UserController, PostController, CommentController, ReelController, StoryController, HomeController
  model/ User, Post, Comments, Reels, Story
  repository/ UserRepository, PostRepository, CommentRepository, ReelRepository, StoryRepository
  services/ *Service + *Implementation (User, Post, Comments, Reel, Story, UserUserDetailService)
  security/ JwtTokenProvider, JwtTokenClaims
  dto/ UserDto — response/ MessageResponse
  exception/ GlobleException (+ ErrorDetails, User/Post/Comment/Story/ReeelException)
  util/ UserUtil
instagram/src/main/resources/ application.properties (+ application-dev/prod.properties)
instagram/src/test/ InstagramApplicationTests
```

## Auth flow

1. `POST /signup` with user JSON → `UserService.registerUser` (BCrypt hash) → 201 + user.
2. `GET /signin` with Basic Auth → Spring `Authentication` → lookup by email → client stores JWT (issued by `JwtTokenProvider`).
3. Subsequent calls send `Authorization: Bearer <jwt>` → `JwtValidationFilter` → `findUserProfile(token)` → owner checks (only author edits/deletes, follow/unfollow, like/unlike).

401 = missing/invalid token, 403 = valid token but not owner/role, via `GlobleException` handler.

## Endpoints

Auth:
| Method | Path | Description |
|---|---|---|
| POST | /signup | Register |
| GET | /signin | Login (Basic Auth, returns user + token flow) |

Users `/api/users` (JWT required unless noted):
| Method | Path |
|---|---|
| GET | /api/users/req (my profile) |
| GET | /api/users/id/{id}, username/{username}, m/{userIds}, search?q=, populer |
| PUT | /api/users/follow/{followUserId}, /unfollow/{unfollowUserId}, /account/edit |

Posts `/api/posts`:
| Method | Path |
|---|---|
| POST | /api/posts/create (header Authorization) |
| GET | /api/posts/, /{postId}, /all/{userId}, /following/{userIds} |
| PUT | /api/posts/like/{postId}, /unlike/{postId}, + save/unsave |
| DELETE | /api/posts/delete/{postId} (owner) |

Comments `/api/comments`, Reels `/api/reels`, Stories `/api/stories` follow the same pattern: create (auth) → get by id / by user → like → delete (owner). See `CommentController`, `ReelController`, `StoryController` for exact paths.

## Run locally

Prerequisites: JDK 17+, Maven (wrapper included), MySQL 8.

```bash
git clone https://github.com/shezad-linux/socialMedia.git
cd socialMedia/instagram

# 1. DB — create schema (default `instagram`)
mysql -u root -p -e "CREATE DATABASE instagram;"

# 2. Configure (env overrides, no secrets in git)
export DB_HOST=localhost DB_PORT=3306 DB_NAME=instagram ENV=dev
# or edit src/main/resources/application.properties:
# spring.datasource.url=jdbc:mysql://localhost:3306/instagram
# spring.datasource.username=root
# spring.datasource.password=

./mvnw spring-boot:run
# -> http://localhost:8080
```

Quick test (Postman):
1. POST `http://localhost:8080/signup` `{name, username, email, password}` → 201
2. GET `http://localhost:8080/signin` (Basic Auth) → get JWT
3. POST `http://localhost:8080/api/posts/create` with `Authorization: Bearer <jwt>` → 201
4. PUT `.../api/posts/like/{postId}` → verify liked list
5. Negative: no header → 401, other user's delete → 403/404

## Config

`application.properties` uses `${DB_HOST:localhost}:${DB_PORT:3306}/${DB_NAME:instagram}` + `${ENV:prod}` profiles (`application-dev/properties`, `application-prod.properties`), `show-sql=true` for debugging.

## What I'd add next

- Move project to repo root (remove `instagram/` nesting), drop `.idea/` from git, add root `.gitignore`
- Pagination on `GET /api/posts/` + `/following` (currently full list), indexes on `user_id, created_at`, fix N+1 with fetch-join/entity-graph
- Refresh-token + short access-token expiry (currently long-lived), 401/403 regression Postman collection + JUnit for service layer

Built by MD Shezad Ansari — linkedin.com/in/shezadansari
