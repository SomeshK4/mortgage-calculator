# Mortgage Calculator

This is an application that reads the file in the format given below and print out the mortgage for each customer using the formula

**Customer,Total loan,Interest,Years**</br>
Juna,1000,5,2</br>
Karvinen,4356,1.27,6</br>
Claes Månsson,1300.55,8.67,2</br>
"Clarencé,Andersson",2000,6,4

**Mortage formula:**

E = U[b(1 + b)^p]/[(1 + b)^p - 1]
where **E** = Fixed monthly payment, **b** = Interest on a monthly basis, **U** = Total loan, **p** = Number of payments

## Tools and Frameworks
1. JAVA 11
2. Maven 3.8.1
3. Spring Boot 2.6.0
4. H2 Database

## Setup project locally 
1. Clone project from github

2. Run command in terminal 

**Windows :**
````
$ mvnw clean install
````

**Linux:**
````
./mvnw clean install
````

## Run the application
1. Run command in terminal and it will load default customer record file bundled within the application

**Windows :**
````
$ mvnw spring-boot:run
````

**Linux:**
````
./mvnw spring-boot:run
````


2. If you want to load customer records file from the file system

**Windows :**
````
$ mvnw spring-boot:run -Dspring-boot.run.arguments=--customer-details.file=<Absolute filePath>
````

**Linux :**
````
$ ./mvnw spring-boot:run -Dspring-boot.run.arguments=--customer-details.file=<Absolute filePath>
````

## Test the application
1. Open your favourite browser and point to URL to see the health of the application
 
   http://localhost:8080/actuator/health

   This should return you response something like -

   ```
   {
       "status":"UP"
    }

   ```


2. Open your favourite browser and point to URL to see the prospects saved in the PROSPECTS table in H2 Database

   http://localhost:8080/h2-console

   JDBC URL: jdbc:h2:mem:mortgagedb

   User Name: sa
   
   Password: password


3. Run the query to see all the prospects 
```
Select * from PROSPECTS;
```


