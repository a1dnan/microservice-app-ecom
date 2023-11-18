package com.a1dnan.orderservice.web;

import com.a1dnan.orderservice.entities.Order;
import com.a1dnan.orderservice.model.Customer;
import com.a1dnan.orderservice.model.Product;
import com.a1dnan.orderservice.repo.OrderRepository;
import com.a1dnan.orderservice.repo.ProductItemRepository;
import com.a1dnan.orderservice.services.CustomerRestClientService;
import com.a1dnan.orderservice.services.ProductRestClientService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderRepository orderRepository;
    private final ProductItemRepository productItemRepository;
    private final CustomerRestClientService customerRestClientService;
    private final ProductRestClientService productRestClientService;

    @GetMapping("/fullOrder/{id}")
    public Order getOrder(@PathVariable Long id){
        Order order = orderRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Order not found"));
        Customer customer = customerRestClientService.customerById(order.getCustomerId());
        order.setCustomer(customer);
        order.getProductItems()
                .forEach(pi -> {
                    Product product=productRestClientService.productById(pi.getProductId());
                    pi.setProduct(product);
                });
        return order;
    }
}
