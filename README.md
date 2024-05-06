# Pack Express Product Microservice

Pack Express Product Microservice is a Spring Boot application designed to handle product management functionalities for the Pack Express package delivery service.

## Table of Contents

* Features
* Technologies Used
* Installation
* API Endpoints

## Features

* **Product CRUD Operations:** Create, Read, Update, and Delete products.
* **Inventory Management:** Track product quantities and availability.
* **Excel:** Get product details in the form of Excel.

## Technologies Used

* **Spring Boot:** Framework for building microservices in Java.
* **Spring Data JPA:** Simplifies data access with ORM for database operations.
* **Hibernate:** ORM tool for mapping Java objects to database tables.
* **MySQL:** Relational database for storing user data.
* **Swagger:** Automated API documentation for easy understanding and testing.

## Installation

* Clone the repository:
```
git clone https://github.com/sudhanshu2900/pack-express-product-microservice.git
```
* Navigate to the project directory:
```
cd pack-express-product-microservice
```
* Set up MySQL database:
  (1) Create a new MySQL database.
  (2) Update the database connection properties in application.properties.
* Build and run the application:
```
mvn spring-boot:run
```
* The application will start running at **http://localhost:9000**.

## API Endpoints
* You will get all the API endpoint on Swagger **http://localhost:9000/swagger-ui/index.html**

NOTE: Microservice will run on port 9000.
