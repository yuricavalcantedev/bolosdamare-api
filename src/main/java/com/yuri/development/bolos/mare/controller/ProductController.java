package com.yuri.development.bolos.mare.controller;

import com.yuri.development.bolos.mare.dto.ProductCreateDTO;
import com.yuri.development.bolos.mare.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ProductCreateDTO productDTO){
        return productService.save(productDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>update(@Valid @PathVariable("id") Long id, @RequestBody ProductCreateDTO productCreateDTO){
        return productService.update(id, productCreateDTO);
    }
    @GetMapping
    public ResponseEntity<?> findAll(){
        return productService.findAll();
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = {"http://localhost:4200"})
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

}
