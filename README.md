# Book Project

This is a Spring Boot project that implements CRUD operations for managing books and the reviews, categories of them. 
The project also includes authentication and authorization features using Spring Security with basic authentication.
Data is stored in a MySQL database.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Endpoints](#endpoints)
- [Authentication](#authentication)
- [Database Configuration](#database-configuration)

## Installation

1. Clone the repository: `git clone https://github.com/AceGeo/springboot-books-rest-api`
2. Navigate to the project directory: `cd springboot-books-rest-api`
3. Build the project: `./mvnw clean install`
4. Run the application: `./mvnw spring-boot:run`

## Usage

The Spring Boot Books Rest API simplifies book management, including categories and user reviews,
with built-in authentication and authorization.

Explore the API with the following steps:

1. Build and Run
Build and run the application:
Access the application at http://localhost:8080.

2. Authentication
 
Certain endpoints require authentication. Make your credentials in order to create 1 admin and 1 user:

3. Postman
 
Use the postman in order to send requests and get responses of the endpoints.  

4. Create a Category

Create a new book category via postman:

Select the POST , add the spesific endpoint, open the authorization field and select basic auth, then add your credentials

5. Add a Category

Add a category with details :
{
    "name": "Science Fiction",
    "description": "Explore imaginative worlds and futuristic concepts in this genre."
}

http://localhost:8080/api/categories

6. Add a Book
   
Add a book with details :

In the field Body , select the "raw" and the "JSON" format from the picklist, then give a JSON request :
{
    "title": "",
    "author": "",
    "language": "",
    "pages": ,
    "releaseDate": "",
    "description": ""
    "category_id" : 
  }
http://localhost:8080/api/books

7. Get All/Get {id} Books

Retrieve a list of all books via "GET" :
http://localhost:8080/api/books

Retrieve a specific book via "GET" :
http://localhost:8080/api/books/{id}

8. Create a Review

Create a review for a book:
{
   "body": "",
   "email": "",
   "name": "",
   "rating": ,
   "book_id":
       }
http://localhost:8080/api/books/{bookId}/reviews

Basic authentication is required for certain endpoints (e.g., creating categories and adding books). Provide valid credentials when prompted.
Ensure the application is running before making API requests. Adjust the base URL (http://localhost:8080) if the application is hosted elsewhere.



## Endpoints

- **/api/books**: CRUD operations for books.
- **/api**: CRUD operations for reviews.
- **/api/categories**: CRUD operations for categories.

Detailed information about each endpoint and request/response examples.

## Authentication

To access protected endpoints, you need to authenticate using basic authentication.

- **Username**: your-username
- **Password**: your-password

## Database Configuration

The application uses MySQL as its database. Ensure you have a MySQL server running and update the `application.properties` file with your database configuration.

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/book_db
spring.datasource.username=root
spring.datasource.password=rootpassword
