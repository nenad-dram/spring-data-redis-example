# spring-data-redis-example

The project demonstrates the usage of Redis with Spring i.e. Spring Data Redis project, and deployment into a local Kubernetes cluster.

## Tech Stack

Spring Data Redis, Spring Boot, Gradle, Docker, Kubernetes

## Description

The application uses Redis as a DB and a cache. Basic CRUD operations for a simple model are
available via exposed API methods.

The following methods are available:

- __POST /api/users__ - add a new user using the request body data (request body is described below)
- __GET /api/users/{id}__ - get data for a user with the given ID
- __PUT /api/users/{id}__ - update a user with the given ID and using the request body data (request
  body is described below)
- __DELETE /api/users/{id}__ - delete a user with the given ID (returns HTTP 204)
- __GET /api/books__ - get all users (without searching or paging)

Request body example for the POST and the PUT method is:  
`{
"firstName": "John",
"lastName": "Doe",
"email":"john.doe@mail.com"
}`

## Starting the application

The application can be started either standalone i.e. using the JAR file, or via Kubernetes.
Details for both options are described in the separate sections below.

### Standalone

To build the fat JAR file (_spring-data-redis-example-*.jar_) execute the command:  
`$ ./gradlew clean bootJar`  
To start the JAR file execute the command:  
`$ java -jar ./build/libs/spring-data-redis-example-*.jar --redis.host=localhost`

### Kubernetes

To build a Docker image first execute the Gradle task `extractBootJar`.
This will generate a jar and extract it into layers required for the image.  
As minikube is not aware of a locale repository the image has to be built from a minikube context.  
The following steps are required:  
`$ minikube docker-env`  
`$ eval $(minikube -p minikube docker-env)`  
Then build the image:  
`$ docker build . -t spring-data-redis-example`

To create a deployments and services execute the following commands:  
`$ kubectl create -f kubernetes/deployment.yaml`  
`$ kubectl create -f kubernetes/service.yaml`

To get the application URL execute the following command:  
`$ minikube service spring-data-redis-service --url`