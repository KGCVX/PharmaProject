## Choices
The source that I used for the H2 in-memory database defines the schema of the SQL table with fields in the code rather than using the **CREATE TABLE** command. Spring boot tried to execute the **INSERT VALUES INTO** commands before starting the database when using the *data.sql* file. The file *import.sql* runs after initializing the database, which avoids this problem. Hence, the presence of the *import.sql* file instead of *data.sql* in the *resources* folder. (Defining the schema in *data.sql* with **CREATE TABLE** would also work, but I preferred the in-code fields.)

## Sources
Creating the REST API: \
https://spring.io/guides/tutorials/rest

Creating the database with spring-data and H2: \
https://docs.spring.io/spring-boot/reference/data/sql.html

https://docs.spring.io/spring-boot/docs/1.1.0.M1/reference/html/howto-database-initialization.html