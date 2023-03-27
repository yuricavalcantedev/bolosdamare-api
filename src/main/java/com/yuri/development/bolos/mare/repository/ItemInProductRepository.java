package com.yuri.development.bolos.mare.repository;

import com.yuri.development.bolos.mare.model.ItemInProduct;
import com.yuri.development.bolos.mare.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemInProductRepository extends JpaRepository<ItemInProduct, Long> {

    List<ItemInProduct> findByProduct(Product product);

    void deleteByProduct(Product product);

}
