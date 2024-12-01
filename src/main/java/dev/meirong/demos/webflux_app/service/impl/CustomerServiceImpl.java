package dev.meirong.demos.webflux_app.service.impl;

import org.springframework.stereotype.Service;

import dev.meirong.demos.webflux_app.model.CustomerModel;
import dev.meirong.demos.webflux_app.repository.CustomerRepository;
import dev.meirong.demos.webflux_app.service.CustomerService;
import lombok.Value;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service("customerService")
@Value
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;


    @Override
    public Mono<CustomerModel> createCustomer(CustomerModel customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Mono<CustomerModel> updateCustomer(CustomerModel customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Mono<Void> removeCustomer(Long customerId) {
        return customerRepository.deleteById(customerId);
    }

    @Override
    public Mono<CustomerModel> findCustomerById(Long customerId) {
        return customerRepository.findById(customerId);
    }

    @Override
    public Flux<CustomerModel> findAllCustomers() {
        return customerRepository.findAll();
    }
    
}
