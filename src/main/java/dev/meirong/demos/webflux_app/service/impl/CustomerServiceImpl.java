package dev.meirong.demos.webflux_app.service.impl;

import java.util.concurrent.TimeUnit;

// import org.redisson.api.RLockReactive;
// import org.redisson.api.RedissonReactiveClient;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import dev.meirong.demos.webflux_app.constant.CacheNames;
// import dev.meirong.demos.webflux_app.constant.LockNames;
import dev.meirong.demos.webflux_app.model.CustomerModel;
import dev.meirong.demos.webflux_app.repository.CustomerRepository;
import dev.meirong.demos.webflux_app.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service("customerService")
@CacheConfig(cacheNames = { CacheNames.CUSTOMER_CACHE })
@AllArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    // private final RedissonReactiveClient redissonReactiveClient;

    @CachePut(key = "#customer.id")
    @Override
    public Mono<CustomerModel> createCustomer(CustomerModel customer) {
        return customerRepository.save(customer);
    }

    @CachePut(key = "#customer.id")
    @Override
    public Mono<CustomerModel> updateCustomer(CustomerModel customer) {
        // String lockKey = LockNames.CUSTOMER_LOCK + customer.getId();
        // RLockReactive lock = redissonReactiveClient.getLock(lockKey);
        // return lock.tryLock(10, 10, TimeUnit.MILLISECONDS)
        //         .flatMap(locked -> {
        //             if (locked) {
        //                 return customerRepository.save(customer)
        //                         .doFinally(signalType -> lock.unlock().subscribe());
        //             } else {
        //                 return Mono.error(new RuntimeException("Could not acquire lock"));
        //             }
        //         });
        return customerRepository.save(customer);
    }

    @CacheEvict(key = "#customerId")
    @Override
    public Mono<Void> removeCustomer(Long customerId) {
        // String lockKey = LockNames.CUSTOMER_LOCK + customerId;
        // RLockReactive lock = redissonReactiveClient.getLock(lockKey);
        // return lock.tryLock(10, 10, TimeUnit.MILLISECONDS)
        //         .flatMap(locked -> {
        //             if (locked) {
        //                 return customerRepository.deleteById(customerId)
        //                         .doFinally(signalType -> lock.unlock().subscribe());
        //             } else {
        //                 return Mono.error(new RuntimeException("Could not acquire lock"));
        //             }
        //         });
        return customerRepository.deleteById(customerId);
    }

    @Cacheable(key = "#customerId", sync=true)
    @Override
    public Mono<CustomerModel> findCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .cache()
                // .subscribeOn(Schedulers.boundedElastic())
                ;// should add .cache() to avoid blocking caching
                // .switchIfEmpty(Mono.just(new CustomerModel(0L, "", "", "")));
    }

    @Override
    public Flux<CustomerModel> findAllCustomers() {
        return customerRepository.findAll()
            .subscribeOn(Schedulers.boundedElastic())
        ;
    }

}
