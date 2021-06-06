# Cake Manager

Cake Manager is a spring boot web application that allows users to upload and view cakes. 

## Prerequisites

You must have java 11 or over and maven installed to run cake manager.

## Usage

Use maven to run

```bash
mvn spring-boot:run
```

Cake manager will run on localhost port 8081 by default. This can be altered in cakemanager/src/main/resources/application.properties.

### Web
Accessing http://localhost:8081/ will take a user to the Desktop homepage where they can view a list of all cakes in the database and view photos.

A user can also add a new cake with a title, description, and upload a jpeg or png image.

### REST endpoint

You can also POST a new cake and GET all cakes as JSON from 

http://localhost:8081/cakes 

