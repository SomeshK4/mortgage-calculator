# Mortgage Calculator

## Tools and Frameworks
1. JAVA 11
2. Maven 3.8.1
3. Spring Boot 2.6.0
4. H2 Database

## Setup project locally 
1. Clone project from github

2. Run command in terminal
    ````
    $ mvnw clean install
    ````

## Run the application
1. Run command in terminal and it will load default customer record file bundled within the application
````
$ mvnw spring-boot:run
````

2. If you want to specify customer records file from the file system
````
$ mvnw spring-boot:run -Dspring-boot.run.arguments=--customer-details.file=<Absolute filePath>
````

2. Open your favourite browser and point to URL to see the health of the application
 
   http://localhost:8080/actuator/health

   This should return you response something like -

   ```
   {
       "status":"UP"
    }

   ```

3. Open your favourite browser and point to URL

   http://localhost:8080/h2-console

   JDBC URL: jdbc:h2:mem:mortgagedb

   User Name: sa
   
   Password: password


4. Run the query to see all the prospects 
```
Select * from PROSPECTS;
```


