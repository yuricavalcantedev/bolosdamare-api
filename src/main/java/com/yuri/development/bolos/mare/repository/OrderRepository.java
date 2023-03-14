package com.yuri.development.bolos.mare.repository;

import com.yuri.development.bolos.mare.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
