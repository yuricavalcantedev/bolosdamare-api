package com.yuri.development.bolos.mare.controller;

import com.yuri.development.bolos.mare.dto.OrderCreateDTO;
import com.yuri.development.bolos.mare.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody OrderCreateDTO orderCreateDTO){
        return orderService.create(orderCreateDTO);
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        return orderService.findaAll();
    }

    public ResponseEntity<?> update(){
        return null;
    }

    @DeleteMapping
    @CrossOrigin(origins = {"http://localhost:4200"})
    public ResponseEntity<?> delete(@PathVariable ("id") Long id){
        return orderService.delete(id);
    }
}
