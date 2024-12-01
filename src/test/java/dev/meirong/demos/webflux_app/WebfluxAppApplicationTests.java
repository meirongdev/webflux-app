package dev.meirong.demos.webflux_app;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.meirong.demos.webflux_app.model.CustomerModel;

@Testcontainers
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebfluxAppApplicationTests {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    ApplicationContext context;

    @Order(1)
    @Test
    void contextLoads() {
    }

    private CustomerModel createRequest(Long id) {
        return new CustomerModel(id,  "John", "Doe", "john.doe@example.com");
    }

    private String createdCustomer() throws JsonProcessingException {
        return objectToJsonString(createRequest(1L));
    }

    private CustomerModel updateCustomer() {
        return new CustomerModel(1L, "Jane", "Doe", "jane.doe@example.com");
    }

    private String updatedCustomer() throws JsonProcessingException {
        return objectToJsonString(updateCustomer());
    }

    private CustomerModel createRequest2(Long id) {
        return new CustomerModel(id, "Alice", "Smith", "alice.smith@example.com");
    }

    private CustomerModel createRequest3(Long id) {
        return new CustomerModel(id, "Bob", "Brown", "bob.brown@example.com");
    }

    private CustomerModel createRequest4(Long id) {
        return new CustomerModel(id, "Charlie", "Davis", "charlie.davis@example.com");
    }

    private String createdCustomers() throws JsonProcessingException {
        List<CustomerModel> customers = List.of(createRequest2(2L), createRequest3(3L), createRequest4(4L));
        return objectToJsonString(customers);
    }

    // @BeforeEach
    // public void setup() {
    //     this.webTestClient = WebTestClient
    //             .bindToApplicationContext(this.context)
    //             .configureClient()
    //             .build();
    // }

    @Order(2)
    @Test
    void testCreateRequest() throws JsonProcessingException {
        this.webTestClient
                .post()
                .uri("/customers")
                .bodyValue(createRequest(null))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody().json(createdCustomer());
    }

    @Order(3)
    @Test
    void testGetRequestOnCreation() throws JsonProcessingException {
        this.webTestClient
                .get()
                .uri("/customers/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody().json(createdCustomer());
    }

    @Order(4)
    @Test
    void testUpdateRequest() throws JsonProcessingException {
        this.webTestClient
                .put()
                .uri("/customers/1")
                .bodyValue(updateCustomer())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody().json(updatedCustomer());
    }

    @Order(5)
    @Test
    void testGetRequestOnUpdate() throws JsonProcessingException {
        this.webTestClient
                .get()
                .uri("/customers/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody().json(updatedCustomer());
    }

    @Order(6)
    @Test
    void testDeleteRequest() throws JsonProcessingException {
        this.webTestClient
                .delete()
                .uri("/customers/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }

    @Order(7)
    @Test
    void testGetRequestOnDelete() throws JsonProcessingException {
        this.webTestClient
                .get()
                .uri("/customers/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }

    @Order(8)
    @Test
    void testGetAllCustomers() throws JsonProcessingException {
        this.webTestClient
                .post()
                .uri("/customers")
                .bodyValue(createRequest2(null))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk(); // created id is 2
        this.webTestClient
                .post()
                .uri("/customers")
                .bodyValue(createRequest3(null))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk(); // created id is 3
        this.webTestClient
                .post()
                .uri("/customers")
                .bodyValue(createRequest4(null))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk(); // created id is 4

        this.webTestClient
                .get()
                .uri("/customers")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody().json(createdCustomers());
    }

    private String objectToJsonString(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

}