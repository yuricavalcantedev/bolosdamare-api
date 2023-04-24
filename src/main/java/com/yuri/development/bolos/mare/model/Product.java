package com.yuri.development.bolos.mare.model;

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
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name can't be empty or null")
    private String name;

    @NotNull
    private BigDecimal price;

    @OneToMany(mappedBy="product", cascade = CascadeType.ALL)
    private List<ItemInProduct> itemsList;

    @ManyToMany(mappedBy = "productsList", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Order> orderList;
}
