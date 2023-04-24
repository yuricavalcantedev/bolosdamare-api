package com.yuri.development.bolos.mare.repository;

import com.yuri.development.bolos.mare.model.ItemInProduct;
import com.yuri.development.bolos.mare.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemInProductRepository extends JpaRepository<ItemInProduct, Long> {

    Long deleteByProduct(Product product);
}
