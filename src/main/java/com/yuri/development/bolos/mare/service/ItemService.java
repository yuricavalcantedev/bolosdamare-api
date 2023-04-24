package com.yuri.development.bolos.mare.service;

import com.yuri.development.bolos.mare.dto.ItemDTO;
import com.yuri.development.bolos.mare.model.Item;
import com.yuri.development.bolos.mare.repository.ItemRepository;
import com.yuri.development.bolos.mare.util.ErrorConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    public ResponseEntity<?> create(ItemDTO itemDTO){

        Optional<Item> optItem = itemRepository.findByName(itemDTO.getName());
        if(optItem.isPresent()){
            return new ResponseEntity<>(ErrorConstants.ITEM_ALREADY_EXISTS, HttpStatus.BAD_REQUEST);
        }

        Item item = adaptItemDTOToItem(itemDTO);
        itemRepository.save(item);

        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    public ResponseEntity<?> update(Long itemId, ItemDTO itemDTO){

        Optional<Item> optItem = itemRepository.findById(itemDTO.getId());
        if(optItem.isEmpty()){
            return new ResponseEntity<>(ErrorConstants.RESOURCE_NOT_FOUND, HttpStatus.NOT_FOUND);
        }

        Item item = adaptItemDTOToItem(itemDTO);
        item.setId(itemId);
        itemRepository.save(item);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    public Optional<Item> findById(Long id){
        return itemRepository.findById(id);
    }

    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(itemRepository.findAll(), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> delete(Long id){

        Optional<Item> optItem = itemRepository.findById(id);
        if(optItem.isEmpty()){
            return new ResponseEntity<>(ErrorConstants.RESOURCE_NOT_FOUND, HttpStatus.NOT_FOUND);
        }

        itemRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private Item adaptItemDTOToItem(ItemDTO itemDTO){

        Item item = new Item();
        item.setName(itemDTO.getName());
        item.setHexColor(itemDTO.getHexColor());
        item.setPrice(itemDTO.getPrice());
        item.setQuantity(itemDTO.getQuantity());
        item.setSupplyType(itemDTO.getSupplyType());

        return item;
    }
}
