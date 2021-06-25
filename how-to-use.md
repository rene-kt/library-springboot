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

 **By the way, you can change the port (8000) to another one, just change the line in ```src/main/resources/application.properties```**

  **Now, you are able to run this Java application locally.** :heavy_check_mark:
