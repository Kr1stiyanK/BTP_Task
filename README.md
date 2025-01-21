# CI/CD Repository and Access Token Manager
This application is a simple CI/CD management tool that allows users to manage repository URLs and access tokens. The backend is built with Java Spring Boot and the frontend is developed using Angular.

## Prerequsites
### Backend Requirements:
**Java**: JDK 17 or higher
**Gradle**: For building the backend application
**MySQL Database**:
  -Ensure MySQL Workbench is installed and running.
  -Create a database named `btp_task`.
  -Update the connection properties in the `application.yml` file

### Frontend Requirements:
**Node.js**: Version 16 or higher (to run Angular)
**Angular CLI**: Installed globally (`npm install -g @angular/cli@latest`)

## Instructions to Run the application

### Step 1: Backend Setup
1. Clone the repository from the cmd:
git clone <repository-url>
cd <repository-folder>

2. Update the databse connection in `src/main/resources/application.yml`:
   spring:
     datasource:
        url: jdbc:mysql://localhost:3306/btp_task?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=Europe/Sofia
        username: <your-username>
        password: <your-password>

     jpa:
        hibernate:
          ddl-auto: create     // for the first run it should be `create`, after the first run of the backend application it shoould be changed to `validate`

3. Build and run the backend application
   CMD:
   .\gradlew bootRun   //For Windows operational system

The backend will start on: `http://localhost:8080`.


### Step 2: Frontend Setup
1. Navigate to the frontend folder (`BTP_FE`)

2. Install dependencies:
   `npm install`

3. Run the Angular application:
   `ng serve`

The frontend will start on: `http://localhost:4200`.

## Application Features
### Backend Endpoints:
1. Repository management:
   - Add, list and delete repository URLs.
   - Validate access tokens with the platform.

2. Access Token Management:
   - Add, list and delete access tokens.
   - Associate repositories with tokens.

### Frontend Features:
1. Fetch URL:
   - Validate repository URLs and access tokens.

2. View Saved Repositories:
   - List all saved repository URLs and delete them.

3. View Access Tokens:
   - List all access tokens with associated repositories and delete them (both).


## Troubleshooting
1. Database Issues:
   - Verify your `application.yml` configuration.
  
2. Frontend Build Errors:
   - Ensure you have installed  the correct Node.js and Angular CLI versions.
   - Delete `node_modules` and reinstall dependencies:
      `rm -rf node_modules`
      `npm install`
