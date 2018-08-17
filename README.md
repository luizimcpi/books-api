# books-api
Books CRUD API using SpringBoot, JPA, H2 DB

Build Status Branch Master

[![Build Status](https://travis-ci.org/luizimcpi/books-api.svg?branch=master)](https://travis-ci.org/luizimcpi/books-api)

Run Application
```
./gradlew bootRun
```

Database Configuration
```
Application use H2 DB to store data when application is running.
If you need, you can use Mysql to store data, just update file application.properties
spring.profiles.active=prod
After that create "books" schema in your mysql -> to change configs look file application-prod.properties
```

Servlet Container Configurations
```
***The application starts at port 8080 but if you want, you can change this configuration setting
the following property in application-test.properties or application-prod.properties
server.port=9090
```

Create Book
```
URL - > http://localhost:8080/books
Method -> POST
Content-Type -> application/json
Body
{
	"title": "Book title example",
	"description": "Book description example",
	"ISBN": "123456",
	"language": "BR"
}
```

Get Book by id
```
URL - > http://localhost:8080/books/{id}
Method -> GET
Content-Type -> application/json
```

Get all Books
```
URL - > http://localhost:8080/books
Method -> GET
Content-Type -> application/json
```