package com.example.bookstore.controller;

import com.example.bookstore.dto.OrderRequest;
import com.example.bookstore.model.CustomerOrder;
import com.example.bookstore.repository.OrderRepository;
import com.example.bookstore.service.BookstoreService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final BookstoreService service;
    private final OrderRepository repository;
    public OrderController(BookstoreService service, OrderRepository repository){this.service=service;this.repository=repository;}
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public CustomerOrder place(@Valid @RequestBody OrderRequest request){return service.placeOrder(request);}
    @GetMapping public List<CustomerOrder> all(){return repository.findAll();}
    @GetMapping("/{id}") public CustomerOrder one(@PathVariable Long id){return repository.findById(id).orElseThrow(()->new org.springframework.web.server.ResponseStatusException(HttpStatus.NOT_FOUND,"Order not found"));}
}
