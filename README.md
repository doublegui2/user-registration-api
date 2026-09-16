# UserRegistrationMS

This project is a small and relatively simple microservice exposing two API endpoints used to register a user into a database, and to view an existing user's stored information.
It is developed with Java and the Springboot framework.

## Requirements

- Java JDK 26
- Springboot 4.1.1
- Maven (not mandatory as you can use the Maven wrapper mvnw instead)

## Database

This project uses an embedded H2 database to store users. It is currently using an in-memory database as the scope of this particular project is still very small, and that allows seamless testing.
For future production, it will still be an embedded database but following the in-file model.

You access the H2 database console at `/h2-console`

## API Endpoints

### Usage

This project exposes two endpoints:
- POST for registration at `/user/registration` (only allows French adult residents)
  - requires a JSON body using this model:
    ````text 
      {
        "username": "guillaume1",
        "birthdate": "2026-10-08",
        "countryOfResidence": "FRA",
        "phoneNumber": "06011897",
        "gender": "MALE"
      }
  - please note that while `username`, `birthdate` and `countryOfResidence` are mandatory, `phoneNumber` and `gender` are entirely optional, you can choose to ignore or use the `null` value
  - `birthdate` *MUST* be in a standard ISO-8601 date format: YYYY-MM-DD
  - `countryOfResidence` *MUST* correspond to a valid ISO-3166 ALPHA3 country code: FRA, USA etc. (see: https://en.wikipedia.org/wiki/List_of_ISO_3166_country_codes)

- GET to view an existing user info at `/user/view/{username}`
  - requires the username as a path variable in the URL: `http://localhost:8080/user/view/user1`

In the `postman` folder you can find a JSON file corresponding to a Postman collection regrouping various requests to showcase the endpoints and their exceptions.
To use it, start the Springboot project as well as Postman. Import the collection into Postman into your local environment, then run either each request individually or the whole collection.
You can run any request in any order with no issue, however if you also run manual request, you *might* add a user that is used in the collection.g

### Error Codes

Here is the list of error codes you can encounter:
| Error Code | Status      | Description                         | 
|------------|-------------|-------------------------------------|
| 400        | Bad Request | Invalid user input                  | 
| 403        | Forbidden   | Age < 18 and/or non-French resident |
| 404        | Not Found   | User not registered                 |
| 409        | Conflict    | Username already taken              |


## Starting the project

For all the following commands, we consider you have installed Maven. If instead you want to use the included Maven wrapper, use `.\mvnw.cmd` on Windows or `./mvnw` on Linux/macOs instead of `mvn`.

Install all the required dependencies:
`mvn clean install`

You can then start the project using the following:
`mvn spring-boot:run`

Alternatively, you can build it into an executable JAR:
``
mvn clean package
java -jar target/user-registration-ms-{version}.jar
``

If you wish to build without running the tests (not recommended), use:
`mvn clean package -DskipTests`

By default, the application runs on port 8080 at `http://localhost:8080`. 
This can be changed by editing the `server.port` entry in application.properties, located under `src/main/resources`.

## Running Tests

Run all unit tests and integration tests with:
`mvn test`