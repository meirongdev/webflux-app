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


## TODO 

- [x] Add spring cache with redis
   - No data in db, then put a default value in cache to avoid hitting db every time
   - use `sync` flag in `@Cacheable` to implement single-flight pattern, to avoid cache stampede
- [x] Add distributed lock with redis for each customer write to avoid to write the same customer concurrently to db
- [ ] Add timeout + retry mechanism for the external service call
- [ ] Add circuit breaker for the external service call
- [ ] Add metrics
    - [ ] jvm
    - [ ] http
    - [ ] r2dbc
    - [ ] cache
    - [ ] circuit breaker
    - [ ] distributed lock
    - [ ] lettuce
    - [ ] timer for code block
    - [ ] custom metrics
    - [ ] netty
- [ ] Add log
- [ ] Add health check
- [ ] Add tracing
- [ ] Add api docs
- [ ] Add postman file