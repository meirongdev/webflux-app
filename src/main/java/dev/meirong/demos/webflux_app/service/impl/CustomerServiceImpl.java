package dev.meirong.demos.webflux_app.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import dev.meirong.demos.webflux_app.constant.CacheNames;
import dev.meirong.demos.webflux_app.model.CustomerModel;
import dev.meirong.demos.webflux_app.repository.CustomerRepository;
import dev.meirong.demos.webflux_app.service.CustomerService;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service("customerService")
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;


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

    @Cacheable(cacheNames ={CacheNames.CUSTOMER_CACHE}, key = "#customerId", sync = true)
    @Override
    public Mono<CustomerModel> findCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
            .log()
            .switchIfEmpty(Mono.just(new CustomerModel(0L, "", "", "")));
    }

    @Override
    public Flux<CustomerModel> findAllCustomers() {
        return customerRepository.findAll();
    }
    
}
