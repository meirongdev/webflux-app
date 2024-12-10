// package dev.meirong.demos.webflux_app.service.impl;

// import static org.mockito.ArgumentMatchers.anyLong;
// import static org.mockito.Mockito.when;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.redisson.api.RedissonReactiveClient;
// import dev.meirong.demos.webflux_app.model.CustomerModel;
// import dev.meirong.demos.webflux_app.repository.CustomerRepository;
// import reactor.core.publisher.Mono;
// import reactor.test.StepVerifier;

// class CustomerServiceImplTest {

//     @Mock
//     private CustomerRepository customerRepository;

//     @Mock
//     private RedissonReactiveClient redissonReactiveClient;

//     @InjectMocks
//     private CustomerServiceImpl customerService;

//     @BeforeEach
//     public void setUp() {
//         MockitoAnnotations.openMocks(this);
//     }

//     @Test
//     void testFindCustomerById_CustomerExists() {
//         CustomerModel customer = new CustomerModel(1L, "John", "Doe", "john.doe@example.com");
//         when(customerRepository.findById(anyLong())).thenReturn(Mono.just(customer));

//         Mono<CustomerModel> result = customerService.findCustomerById(1L);

//         StepVerifier.create(result)
//                 .expectNext(customer)
//                 .verifyComplete();
//     }

//     @Test
//     void testFindCustomerById_CustomerDoesNotExist() {
//         when(customerRepository.findById(anyLong())).thenReturn(Mono.empty());

//         Mono<CustomerModel> result = customerService.findCustomerById(1L);

//         StepVerifier.create(result)
//                 .verifyComplete();
//     }
// }