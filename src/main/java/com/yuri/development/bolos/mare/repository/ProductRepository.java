package com.yuri.development.bolos.mare.repository;

import com.yuri.development.bolos.mare.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
