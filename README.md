# Library API using Spring Boot :coffee:



## Technologies :computer:

- Java
- Spring boot
- Spring Data JPA 
- Spring security 
- JWT
- Swagger 
- PostgreSQL
- Heroku (to deploy the application) 

## Features :fire: 

- Sign up and Log in
- Create books
- List books 
- Reserve books (with expiration date and automatic devolution) 
- Devolve books

## How to use :wave: (IF YOU WANT TO USE IT LOCALLY) 

To clone and run this application by yourself, make sure you have at least Java 8 and all JDK stuff (including JRE), Maven to build the dependencies,
Ecplise or STS, and Postman (it's not necessary, though it's really useful to handle a rest API. After that, do the following instructions

- Clone this repository
```bash
$ git clone https://github.com/reness0/library-springboot
```

- Open this project using Eclipse or Spring tool Suit

- Replace the ```src/main/resources/application.properties``` with the following configuration: 


## Replace this: 

```
spring.main.banner-mode=off
logging.level.org.springframework=ERROR

spring.jpa.hibernate.ddl-auto=update
spring.datasource.driverClassName=org.postgresql.Driver
spring.datasource.maxActive=10
spring.datasource.maxIdle=5
spring.datasource.minIdle=2
spring.datasource.initialSize=5
spring.datasource.removeAbandoned=true
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true

```

## To this: 
``` 
spring.jpa.database=POSTGRESQL
spring.datasource.platform=postgres
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.database.driverClassName=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5400/library
spring.datasource.username=postgres
spring.datasource.password=admin
server.port=8000
```




- Run ```src/com/rene/library/LibraryApplication.java```
  > This is gonna be building the maven dependencies too

- The endpoints are located in 'http://localhost:8000/' you can config its port on ```src/main/resources/application.properties```
  > Use a software like postman to do the resquests. 
  
- Make sure to create a database called **library** 
  > or create it with another name. However, you must to rename its name in ```src/main/resources/application.properties```

 **By the way, you can change the port (3000) to another one, just change the line in ```src/main/resources/application.properties```**

  **Now, you are able to run this Java application locally.** :heavy_check_mark:


## If you only want to use the API without cloning the project 

- Open a software like postman to do the requests
- Acess the endpoints through  https://library-springboot-api.herokuapp.com

## Usage of the application 

In this [document](usage.md), there are some steps that you must to follow to use this application. I recommend to take a read before
start using it.

# API Documentation :memo:

Swagger is responsible to provide a documentation of the API, it break down the endpoints and the models of the application.
Check it out:  https://library-springboot-api.herokuapp.com/swagger-ui.html

![image](https://user-images.githubusercontent.com/49681380/123425989-31f84980-d599-11eb-9c34-9bc656697e79.png)



## How to contribute :question:

1. Make a fork;
2. Create a branch with your feature: `git checkout -b my-feature`;
3. Commit changes: `git commit -m 'Creating new classes'`;
4. Push the changes: `git push origin my-feature`.





