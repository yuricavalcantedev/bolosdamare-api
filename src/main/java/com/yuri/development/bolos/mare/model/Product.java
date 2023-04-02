package com.yuri.development.bolos.mare.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "items"})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @NotBlank(message = "name can't be empty or null")
    private String name;

    @NotNull
    private BigDecimal price;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "products_items",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> itemsList;

    @ManyToMany(mappedBy = "productsList", fetch = FetchType.LAZY)
    private List<Order> orderList;
}
