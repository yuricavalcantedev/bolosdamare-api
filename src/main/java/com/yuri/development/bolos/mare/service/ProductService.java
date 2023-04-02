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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ResponseEntity<?> save(ProductCreateDTO productDTO) {

        Optional<Product> optProduct = productRepository.findByName(productDTO.getName());
        if (optProduct.isPresent()) {
            return new ResponseEntity<>(ErrorConstants.PRODUCT_ALREADY_EXISTS, HttpStatus.BAD_REQUEST);
        }

        Product product = new Product();
        adaptToProduct(product, productDTO);

        product = productRepository.save(product);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> update(Long id, ProductCreateDTO productCreateDTO) {

        Optional<Product> optProduct = productRepository.findById(id);
        if (optProduct.isEmpty()) {
            return new ResponseEntity<>(ErrorConstants.RESOURCE_NOT_FOUND, HttpStatus.NOT_FOUND);
        }

        adaptToProduct(optProduct.get(), productCreateDTO);
        productRepository.save(optProduct.get());

        return new ResponseEntity<>(optProduct.get(), HttpStatus.OK);
    }

    public ResponseEntity<?> findAll() {

        List<Product> productList = productRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();

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
        product.setItemsList(dto.getItemsList());

    }

    private ProductDTO adaptProductToDTO(Product product){

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setItems(product.getItemsList());

        return  productDTO;
    }
}
