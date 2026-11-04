# Opdracht sollicitatie
Naam: Koen Gallé

## Running the API
Run the ```PharmaPractiseApplication.main()``` function. The REST API will subsequently be active on 
http://localhost:8080. Next, the HTTP requests from the assignment can be send to . I did not manage to create the bonus
front-end in time, but http://localhost:8080/h2-console can be used. Note that the *JDBC URL* of the database is: 
*jdbc:h2:mem:CRYPTO*. There is no password. The logging can be found in the file *history.log*, which is located in the 
root.

## What is included
The following aspects of the assignment are included:
- REST API (POST, GET (single), GET (all), PUT, DELETE)
- Serialisation to JSON
- Paging and sorting
- Predefined records
- Logging using slf4j

## What is NOT included
There are no unit tests for the REST API. I did not manage to connect to the database from the test file. Instead, I 
tested the code with Postman. By starting the API on http://localhost:8080 and sending HTTP requests with Postman I 
confirmed that the API worked for me. To at least show that the API works I included images from Postman in the *tests* 
folder in the root. 

I was working on the building design pattern, but could not finish due to time constraints.

## Sources
Creating the REST API: \
https://spring.io/guides/tutorials/rest

Creating the database with spring-data and H2: \
https://docs.spring.io/spring-boot/reference/data/sql.html

https://docs.spring.io/spring-boot/docs/1.1.0.M1/reference/html/howto-database-initialization.html

Creating a log containing information about interactions with the API: \
https://www.slf4j.org/manual.html