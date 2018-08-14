# books-api
Books CRUD API using SpringBoot, JPA, H2 DB

Build Status Branch Master

[![Build Status](https://travis-ci.org/luizimcpi/ecommerce-api.svg?branch=master)](https://travis-ci.org/luizimcpi/ecommerce-api)


Configurações de DB
```
Create "books" schema in mysql in file application.properties
```

Run Application
```
./gradlew bootRun
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