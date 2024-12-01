package dev.meirong.demos.webflux_app.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import dev.meirong.demos.webflux_app.model.CustomerModel;

public interface CustomerRepository extends ReactiveCrudRepository<CustomerModel, Long> {
}
