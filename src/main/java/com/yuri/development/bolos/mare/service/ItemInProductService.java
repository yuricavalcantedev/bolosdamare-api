package com.yuri.development.bolos.mare.service;

import com.yuri.development.bolos.mare.model.Item;
import com.yuri.development.bolos.mare.model.ItemInProduct;
import com.yuri.development.bolos.mare.model.Product;
import com.yuri.development.bolos.mare.repository.ItemInProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ItemInProductService {

    private final ItemInProductRepository itemInProductRepository;

    private final ItemService itemService;

    @Autowired
    public ItemInProductService(ItemInProductRepository itemInProductRepository, ItemService itemService){
        this.itemInProductRepository = itemInProductRepository;
        this.itemService = itemService;
    }

    @Transactional
    public List<ItemInProduct> saveAll(Product product, List<ItemInProduct> itemInProductList){

        for(int i = 0; i < itemInProductList.size(); i ++){

            ItemInProduct item = itemInProductList.get(i);
            Optional<Item> itemEntity = itemService.findById(item.getItemId());

            if(itemEntity.isEmpty()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "");
            }
            item.setProduct(product);
            itemInProductList.set(i, itemInProductRepository.save(item));
        }
        return itemInProductList;
    }

    public List<ItemInProduct> findByProduct(Product product){
        return itemInProductRepository.findByProduct(product);
    }

    public void deleteByProduct(Product product){
        itemInProductRepository.deleteByProduct(product);
    }

}