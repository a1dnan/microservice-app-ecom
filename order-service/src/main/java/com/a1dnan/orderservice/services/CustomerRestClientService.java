package com.a1dnan.orderservice.services;

import com.a1dnan.orderservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "customer-service")
public interface CustomerRestClientService {

    @GetMapping("/customers/{id}")
    public Customer customerById(@PathVariable Long id);

    @GetMapping("/customers")
    public PagedModel<Customer> allCustomers();
}
