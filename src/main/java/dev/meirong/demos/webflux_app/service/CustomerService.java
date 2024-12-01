package dev.meirong.demos.webflux_app.service;

import dev.meirong.demos.webflux_app.model.CustomerModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
    Mono<CustomerModel> createCustomer(CustomerModel customer);

    Mono<CustomerModel> updateCustomer(CustomerModel customer);

    Mono<Void> removeCustomer(Long customerId);

    Mono<CustomerModel> findCustomerById(Long customerId);

    Flux<CustomerModel> findAllCustomers();
}
