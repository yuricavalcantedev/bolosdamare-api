package com.yuri.development.bolos.mare.service;

import com.yuri.development.bolos.mare.dto.ItemInProductDTO;
import com.yuri.development.bolos.mare.dto.ProductCreateDTO;
import com.yuri.development.bolos.mare.dto.ProductDTO;
import com.yuri.development.bolos.mare.model.Item;
import com.yuri.development.bolos.mare.model.ItemInProduct;
import com.yuri.development.bolos.mare.model.Product;
import com.yuri.development.bolos.mare.repository.ProductRepository;
import com.yuri.development.bolos.mare.util.ErrorConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ItemInProductService itemInProductService;

    private final ItemService itemService;

    @Autowired
    public ProductService(ProductRepository productRepository, ItemInProductService itemInProductService, ItemService itemService) {
        this.productRepository = productRepository;
        this.itemInProductService = itemInProductService;
        this.itemService = itemService;
    }

    public ResponseEntity<?> save(ProductCreateDTO productDTO) {

        Optional<Product> optProduct = productRepository.findByName(productDTO.getName());
        if (optProduct.isPresent()) {
            return new ResponseEntity<>(ErrorConstants.PRODUCT_ALREADY_EXISTS, HttpStatus.BAD_REQUEST);
        }

        Product product = adaptToProduct(productDTO);
        List<ItemInProduct> itemInProductList = product.getItemInProductList();
        product.setItemInProductList(null);
        product = productRepository.save(product);

        product.setItemInProductList(itemInProductService.saveAll(product, itemInProductList));
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> update(Long id, ProductCreateDTO productCreateDTO) {

        Optional<Product> optProduct = productRepository.findById(id);
        if (optProduct.isEmpty()) {
            return new ResponseEntity<>(ErrorConstants.RESOURCE_NOT_FOUND, HttpStatus.NOT_FOUND);
        }

        List<ItemInProduct> itemInProductList = productCreateDTO.getItemInProductList().stream()
                .map(itemDto -> new ItemInProduct(itemDto.getItemId(), itemDto.getQuantity(), itemDto.getPrice()))
                .collect(Collectors.toList());

        itemInProductService.deleteByProduct(optProduct.get());
        productRepository.save(optProduct.get());
        optProduct.get().setItemInProductList(itemInProductService.saveAll(optProduct.get(), itemInProductList));

        return new ResponseEntity<>(optProduct.get(), HttpStatus.OK);
    }

    public ResponseEntity<?> findAll() {

        List<Product> productList = productRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();

        for(Product product : productList){
            List<ItemInProductDTO> itemInProductDTOList = product.getItemInProductList().stream()
                    .map(item -> adaptItemInProductToDTO(item)).collect(Collectors.toList());

            productDTOList.add(adaptProductToDTO(product, itemInProductDTOList));
        }

        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }

    public ResponseEntity<?> delete(Long id){

        Optional<Product> optProduct = productRepository.findById(id);
        if(optProduct.isEmpty()){
            return new ResponseEntity<>(ErrorConstants.RESOURCE_NOT_FOUND, HttpStatus.NOT_FOUND);
        }

        productRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private Product adaptToProduct(ProductCreateDTO dto) {

        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());

        List<ItemInProduct> itemInProductList = dto.getItemInProductList().stream()
                .map(itemDto -> new ItemInProduct(itemDto.getItemId(), itemDto.getQuantity(), itemDto.getPrice()))
                .collect(Collectors.toList());

        product.setItemInProductList(itemInProductList);
        return product;
    }

    private ProductDTO adaptProductToDTO(Product product, List<ItemInProductDTO> itemInProductDTOList){

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setItemInProductDTOList(itemInProductDTOList);

        return  productDTO;
    }

    private ItemInProductDTO adaptItemInProductToDTO(ItemInProduct itemInProduct){

        ItemInProductDTO itemInProductDTO = new ItemInProductDTO();
        itemInProductDTO.setId(itemInProduct.getId());
        itemInProductDTO.setPrice(itemInProduct.getPrice());
        itemInProductDTO.setQuantity(itemInProduct.getQuantity());

        Optional<Item> optItem = itemService.findById(itemInProduct.getItemId());
        optItem.ifPresent(itemInProductDTO::setItem);

        return itemInProductDTO;
    }

}
