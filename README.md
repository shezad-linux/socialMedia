

### README.md

```markdown
# socialMedia



## Endpoints

### Actuator Endpoints
The following are the default actuator endpoints provided by Spring Boot:

#### Health Check
- **URL**: `/actuator/health`
- **Method**: `GET`
- **Description**: Provides health status of the application.

#### Beans
- **URL**: `/actuator/beans`
- **Method**: `GET`
- **Description**: Displays a complete list of all Spring beans in your application.

#### Caches
- **URL**: `/actuator/caches`
- **Method**: `GET`
- **Description**: Exposes available caches.

#### Conditions
- **URL**: `/actuator/conditions`
- **Method**: `GET`
- **Description**: Provides information about the conditions on which the configuration of the application depends.

#### Config Props
- **URL**: `/actuator/configprops`
- **Method**: `GET`
- **Description**: Displays a collated list of all @ConfigurationProperties.

#### Environment
- **URL**: `/actuator/env`
- **Method**: `GET`
- **Description**: Exposes properties from the Spring Environment.

#### Heap Dump
- **URL**: `/actuator/heapdump`
- **Method**: `GET`
- **Description**: Returns a GZip compressed hprof heap dump file.

#### Loggers
- **URL**: `/actuator/loggers`
- **Method**: `GET`
- **Description**: Shows and modifies the configuration of loggers in the application.

#### Mappings
- **URL**: `/actuator/mappings`
- **Method**: `GET`
- **Description**: Displays a list of all @RequestMapping paths.

#### Metrics
- **URL**: `/actuator/metrics`
- **Method**: `GET`
- **Description**: Shows 'metrics' information for the current application.

#### Scheduled Tasks
- **URL**: `/actuator/scheduledtasks`
- **Method**: `GET`
- **Description**: Provides information about scheduled tasks within the application.

#### Thread Dump
- **URL**: `/actuator/threaddump`
- **Method**: `GET`
- **Description**: Provides a thread dump.

### Custom Endpoints

#### Find Chat Messages
- **URL**: `/api/messages/chat/{chatId}`
- **Method**: `GET`
- **Description**: Retrieves messages for a given chat ID.

#### Find Post By ID
- **URL**: `/posts/{postId}`
- **Method**: `GET`
- **Description**: Retrieves a post by its ID.

#### Create Chat
- **URL**: `/api/chats`
- **Method**: `POST`
- **Description**: Creates a new chat.

#### Find User By Email
- **URL**: `/api/user/email`
- **Method**: `GET`
- **Description**: Finds a user by their email.

#### Create Post
- **URL**: `/posts/user/{userId}`
- **Method**: `POST`
- **Description**: Creates a new post for a user.

#### Create Comment
- **URL**: `/api/comments/post/{postId}`
- **Method**: `POST`
- **Description**: Creates a new comment on a post.

### Error Handling

#### Default Error Handling
- **URL**: `/error`
- **Method**: Various
- **Description**: Default error handling endpoint.

### Usage
To access these endpoints, use the base URL `http://localhost:8080` (or your application's base URL) followed by the specific endpoint path. Ensure that your application is running and that you have the appropriate permissions to access these endpoints.

## How to Enable Actuator Endpoints
In your `application.properties` or `application.yml` file, you can enable additional actuator endpoints by adding:
```properties
management.endpoints.web.exposure.include=*

## Further Information
For more information about Spring Boot Actuator, refer to the [official documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html).

