# spring-reactive

[![License: WTFPL](https://img.shields.io/badge/License-WTFPL-brightgreen.svg)](http://www.wtfpl.net/about/)  
Reactive CRUD with Spring WebFlux, embedded mongoDB and integration tests

## Running the application locally

  ```shell
  git clone https://github.com/DOKL57/spring-reactive.git
  cd ./spring-reactive
  mvn spring-boot:run
  ```



## Explore Rest APIs

The app defines following CRUD APIs.

### Products

| Method | Url                        | Description                                        | 
--------|--------|----------------------------|--------------------------------------------|
| GET     | /products                     | Get list of all products                       |
| GET     | /products/{id}                | Get product by {id}                            |
| GET     | /products/range               | Get all products with price from {min} to {max}|
| POST    | /products/                    | Add new product                                |
| PUT     | /products/update/{id}         | Update product with {id}                       |
| DELETE  | /products/delete/{id}         | Delete product with {id}                       |



