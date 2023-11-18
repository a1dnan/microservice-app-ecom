package com.a1dnan.customerservice;

import com.a1dnan.customerservice.entities.Customer;
import com.a1dnan.customerservice.repo.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepository, RepositoryRestConfiguration restConfiguration){
        return args -> {
            restConfiguration.exposeIdsFor(Customer.class);
            customerRepository.saveAll(List.of(
                    Customer.builder()
                            .name("Adnan")
                            .email("adnan@gmail.com")
                            .build(),
                    Customer.builder()
                            .name("Mohamed")
                            .email("med@gmail.com")
                            .build(),
                    Customer.builder()
                            .name("Ali")
                            .email("ali@gmail.com")
                            .build()
            ));
            customerRepository.findAll().forEach(System.out::println);
        };
    }
}
