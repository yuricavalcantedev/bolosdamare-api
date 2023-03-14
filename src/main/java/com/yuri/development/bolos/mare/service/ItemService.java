package com.yuri.development.bolos.mare.service;

import com.yuri.development.bolos.mare.ItemDTO;
import com.yuri.development.bolos.mare.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    public ResponseEntity<?> create(ItemDTO itemDTO){
        return null;
    }

    public ResponseEntity<?> update(){
        return null;
    }

    public ResponseEntity<?> findAll(){
        return null;
    }

    public void delete(Long id){

    }
}
