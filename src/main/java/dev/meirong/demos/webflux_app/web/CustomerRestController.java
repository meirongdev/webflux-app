package dev.meirong.demos.webflux_app.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.meirong.demos.webflux_app.model.CustomerModel;
import dev.meirong.demos.webflux_app.service.CustomerService;
import lombok.Value;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Value
@RestController
public class CustomerRestController {
    CustomerService customerService;

    // curl -X GET -H "Content-Type: application/json" http://localhost:8080/customers/1
    // hey -n 100000 -c 100 -m GET http://localhost:8080/customers/1
    @GetMapping("/customers/{id}")
    Mono<CustomerModel> getCustomerById(@PathVariable("id") Long id) {
        return customerService.findCustomerById(id)
            .switchIfEmpty(Mono.error(new RuntimeException("Customer not found")))
            .subscribeOn(Schedulers.boundedElastic());
    }

    // curl -X POST -H "Content-Type: application/json" -d '{"companyName":"John Doe","companyEmail":"abc@gmail.com", "taxId": "123"}' http://localhost:8080/customers
    // hey -n 1000 -c 10 -m POST -T "application/json" -d '{"companyName":"John Doe","companyEmail":"abc@gmail.com", "taxId": "123"}' http://localhost:8080/customers
    @PostMapping("/customers")
    Mono<CustomerModel> postCustomer(@RequestBody CustomerModel customer) {
        return customerService.createCustomer(customer);
    }

    // curl -X PUT -H "Content-Type: application/json" -d '{"id": 1, "companyName":"John Doe","companyEmail":"updated@gmail.com", "taxId": "123"}' http://localhost:8080/customers/1
    @PutMapping("/customers/{id}")
    Mono<CustomerModel> putCustomer(@PathVariable("id") Long id, @RequestBody CustomerModel customer) {
        return customerService.createCustomer(customer);
    }

    // curl -X GET -H "Content-Type: application/json" http://localhost:8080/customers
    // hey -n 100 -c 10 -m GET http://localhost:8080/customers
    @GetMapping("/customers")
    Flux<CustomerModel> getAllCustomers() {
        return customerService.findAllCustomers();
    }

    // curl -X DELETE -H "Content-Type: application/json" http://localhost:8080/customers/1
    // hey -n 100 -c 10 -m DELETE http://localhost:8080/customers/1
    @DeleteMapping("/customers/{id}")
    Mono<Void> deleteCustomer(@PathVariable("id") Long id) {
        return customerService.removeCustomer(id);
    }
}
