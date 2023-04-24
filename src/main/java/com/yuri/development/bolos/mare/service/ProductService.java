package com.yuri.development.bolos.mare.service;

import com.yuri.development.bolos.mare.dto.ProductCreateDTO;
import com.yuri.development.bolos.mare.dto.ProductDTO;
import com.yuri.development.bolos.mare.model.Product;
import com.yuri.development.bolos.mare.repository.ProductRepository;
import com.yuri.development.bolos.mare.util.ErrorConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ItemInProductService itemInProductService;

    @Autowired
    public ProductService(ProductRepository productRepository, ItemInProductService itemInProductService) {
        this.productRepository = productRepository;
        this.itemInProductService = itemInProductService;
    }

    @Transactional
    public ResponseEntity<?> save(ProductCreateDTO productDTO) {

        Optional<Product> optProduct = productRepository.findByName(productDTO.getName());
        if (optProduct.isPresent()) {
            return new ResponseEntity<>(ErrorConstants.PRODUCT_ALREADY_EXISTS, HttpStatus.BAD_REQUEST);
        }

        Product product = new Product();
        adaptToProduct(product, productDTO);
        productRepository.save(product);

        productDTO.getItemsList().forEach(item -> item.setProduct(product));
        product.setItemsList(productDTO.getItemsList());

        productRepository.save(product);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> update(Long id, ProductCreateDTO productCreateDTO) {

        Optional<Product> optProduct = productRepository.findById(id);
        if (optProduct.isEmpty()) {
            return new ResponseEntity<>(ErrorConstants.RESOURCE_NOT_FOUND, HttpStatus.NOT_FOUND);
        }

        Product product = optProduct.get();

        adaptToProduct(product, productCreateDTO);
        itemInProductService.deleteByProduct(product);
        productRepository.save(product);

        productCreateDTO.getItemsList().forEach(item -> item.setProduct(product));
        product.setItemsList(productCreateDTO.getItemsList());
        productRepository.save(product);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    public ResponseEntity<?> findAll() {

        List<Product> productList = productRepository.findAll();
        List<ProductDTO> productDTOList;

        productDTOList = productList.stream()
                .map(this::adaptProductToDTO).collect(Collectors.toList());
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

    private void adaptToProduct(Product product, ProductCreateDTO dto) {

        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setItemsList(null);
    }

    private ProductDTO adaptProductToDTO(Product product){

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setItemsList(product.getItemsList());

        return  productDTO;
    }
}
