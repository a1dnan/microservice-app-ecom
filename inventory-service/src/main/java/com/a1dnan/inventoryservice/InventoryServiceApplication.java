package com.a1dnan.inventoryservice;

import com.a1dnan.inventoryservice.entities.Product;
import com.a1dnan.inventoryservice.repo.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration restConfiguration){
        return args -> {
            restConfiguration.exposeIdsFor(Product.class);
            productRepository.saveAll(List.of(
                    Product.builder()
                            .name("Lenovo")
                            .price(8000)
                            .quantity(2)
                            .build(),
                    Product.builder()
                            .name("Samsung")
                            .price(9000)
                            .quantity(4)
                            .build(),
                    Product.builder()
                            .name("PS5")
                            .price(7000)
                            .quantity(6)
                            .build()
            ));
            productRepository.findAll().forEach(System.out::println);
        };
    }
}
