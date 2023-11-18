package com.a1dnan.orderservice.services;

import com.a1dnan.orderservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "inventory-service")
public interface ProductRestClientService {

    @GetMapping("/products/{id}")
    public Product productById(@PathVariable Long id);

    @GetMapping("/products")
    public PagedModel<Product> allProducts();
}
