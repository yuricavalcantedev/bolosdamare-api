package com.yuri.development.bolos.mare.repository;

import com.yuri.development.bolos.mare.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
}
