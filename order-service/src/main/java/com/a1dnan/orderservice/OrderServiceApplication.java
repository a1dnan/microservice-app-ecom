package com.a1dnan.orderservice;

import com.a1dnan.orderservice.entities.Order;
import com.a1dnan.orderservice.entities.ProductItem;
import com.a1dnan.orderservice.enums.OrderStatus;
import com.a1dnan.orderservice.model.Customer;
import com.a1dnan.orderservice.model.Product;
import com.a1dnan.orderservice.repo.OrderRepository;
import com.a1dnan.orderservice.repo.ProductItemRepository;
import com.a1dnan.orderservice.services.CustomerRestClientService;
import com.a1dnan.orderservice.services.ProductRestClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(OrderRepository orderRepository,
                            ProductItemRepository productItemRepository,
                            CustomerRestClientService customerRestClientService,
                            ProductRestClientService productRestClientService,
                            RepositoryRestConfiguration restConfiguration){
        return args -> {
            restConfiguration.exposeIdsFor(Order.class);
            List<Customer> customers = customerRestClientService.allCustomers().getContent().stream().toList();
            List<Product> products = productRestClientService.allProducts().getContent().stream().toList();

            Long customerId = 1L;
            Random random = new Random();
            Customer customer = customerRestClientService.customerById(customerId);
            for (int i = 0 ; i<10 ;i++){
                Order order = Order.builder()
                        .customerId(customers.get(random.nextInt(customers.size())).getId())
                        .status(Math.random()>0.5? OrderStatus.CREATED: OrderStatus.PENDING)
                        .createdAt(new Date())
                        .build();
                Order savedOrder = orderRepository.save(order);
                for (int j = 0 ; j < products.size(); j++){
                    if(Math.random()>0.70){
                        ProductItem productItem = ProductItem.builder()
                                .order(savedOrder)
                                .productId(products.get(j).getId())
                                .price(products.get(j).getPrice())
                                .quantity(1+ random.nextInt(10))
                                .discount(Math.random())
                                .build();
                        productItemRepository.save(productItem);
                    }
                }
            }
        };
    }
}
