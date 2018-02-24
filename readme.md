# JSON-API Demo

Simple Spring Boot application that provides a JSON-API interface with Swagger API documentation.

# Features
- Swagger UI for calling the microservice
- JSON-API query parameters for include/fields/sort/filter
- Simple mocked repositories

# Outstanding Work
- Investigate how the JSON-API repositories should work
- Implement a persistent store
- Add CRUD operations

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

### Prerequisites

* Java 8 or higher
* Git

### Installing

A step by step series of examples that tell you have to get a development env running

Clone the git repository locally 

```
>git clone https://github.com/richard-allwood/jasonapi-demo.git
```

Run the Spring Boot application

#### Windows
```
>cd jasonapi-demo
>gradlew bootRun
```

#### Linux/Mac
```
$ cd jasonapi-demo
$ ./gradlew bootRun
```

Navigate to the Spring Boot application page
````
http://localhost:8080/swagger-ui.html
````


## Running the tests

- TODO

### Break down into end to end tests

- TODO

## Built With

* [SpringBoot](https://projects.spring.io/spring-boot/) - The microservice framework
* [Springfox](http://springfox.github.io/springfox/) - Making Spring MVC restful
* [Gradle](https://gradle.org/) - The best build tool around
* [Lombok](https://projectlombok.org/) - Life is too short for getters and setters

## Authors

* **Richard Allwood** - *Initial work* 

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Based on lots of code around the internet - special callout to http://www.baeldung.com for so many useful tutorials
