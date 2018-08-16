# books-api
Books CRUD API using SpringBoot, JPA, H2 DB

Build Status Branch Master

[![Build Status](https://travis-ci.org/luizimcpi/books-api.svg?branch=master)](https://travis-ci.org/luizimcpi/books-api)


Configurações de DB
```
Create "books" schema in mysql -> to change configs look file application-prod.properties
```

Run Application
```
./gradlew bootRun
```

***The application starts at port 8080 but if you want, you can change this configuration setting
the following property in application-prod.properties

```
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