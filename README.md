# Project Documentation

### Usage

To access these endpoints, use the base URL `http://localhost:8080` (or your application's base URL) followed by the specific endpoint path. Ensure that your application is running and you have the necessary permissions to access these endpoints.

## Running the Project Locally

Follow these steps to run the project locally on your machine.

### Prerequisites

Ensure you have the following installed on your machine:

1. **Java Development Kit (JDK)**: Version 11 or later.
2. **Maven**: For dependency management and building the project.
3. **MySQL**: Or another relational database configured in `application.properties`/`application.yml`.
4. **Git**: For cloning the repository.

### Steps to Run Locally

#### 1. Clone the Repository
Open your terminal and clone the repository:
```bash
git clone [https://github.com/your-username/socialMedia.git]
2. Navigate to the Project Directory
Move into the project directory:


cd socialMedia
3. Configure the Database
Set up the database that the application will use. Update the application.properties or application.yml file with your local database credentials:


spring.datasource.url=jdbc:mysql://localhost:3306/social_media_db
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
4. Install Dependencies
Use Maven to install the required dependencies:


mvn clean install
5. Run the Application
Start the application using Maven:


mvn spring-boot:run
Alternatively, you can run the compiled JAR file:


java -jar target/socialMedia-0.0.1-SNAPSHOT.jar
6. Access the Application
Once the application starts, you can access it in your browser at:


http://localhost:8080
Troubleshooting
Port Issues: If port 8080 is already in use, you can change it in the application.properties:


server.port=8081
Database Connection Errors: Ensure that MySQL is running and that the credentials in application.properties match your local setup.
