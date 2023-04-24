package com.yuri.development.bolos.mare.service;

import com.yuri.development.bolos.mare.model.ItemInProduct;
import com.yuri.development.bolos.mare.model.Product;
import com.yuri.development.bolos.mare.repository.ItemInProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemInProductService {

    private ItemInProductRepository itemInProductRepository;

    @Autowired
    public ItemInProductService(ItemInProductRepository itemInProductRepository){
        this.itemInProductRepository = itemInProductRepository;
    }
    public void saveAll(List<ItemInProduct> itemList){
        itemInProductRepository.saveAll(itemList);
    }

    public Long deleteByProduct(Product product){
        return itemInProductRepository.deleteByProduct(product);
    }
}
