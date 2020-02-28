# Readme

**How to use the service**

Start the server from terminal
```text
mvn spring-boot:run
```
Check local DB in memory in browser: http://localhost:8080/h2-console/
```text
url=jdbc:h2:mem:testdb
username=sa
password=password
```
Use curl/postman to access the database: http://localhost:8080

|Methods| Urls                          | Actions |
| :---  | :---------------------------- | :------ |
|POST	| /api/examples	                | create new Example
|GET	| /api/examples	                | retrieve all Examples
|GET	| /api/examples/:id	            | retrieve a Example by :id
|PUT	| /api/examples/:id	            | update a Example by :id
|DELETE	| /api/examples/:id	            | delete a Example by :id
|DELETE	| /api/examples	                | delete all Examples
|GET	| /api/examples?title=[keyword] | find all Examples which title contains keyword
