# Webflux APp

This is a simple webflux demo that uses the following technologies:
- spring boot 3.4.0
- spring webflux
- spring data r2dbc
- r2dbc postgres
- spring boot actuator
- spring boot cache
- hibernate validator
- lombok


## Running the application

locally
```shell
make up-db
./mvnw spring-boot:run
```
run all in the containers
```shell
make build
make up
```

## Running the tests

curl commands can see [here](./src/main/java/dev/meirong/demos/webflux_app/web/CustomerRestController.java).

```shell
./mvnw test
```