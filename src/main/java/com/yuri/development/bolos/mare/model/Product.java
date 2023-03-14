package com.yuri.development.bolos.mare.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "receipt")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receipt_id")
    private Long id;

    @NotBlank(message = "name can't be empty or null")
    private String name;

    private String description;

    @ManyToMany
    @JoinTable(
            name = "item",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> itemList;

    @ManyToMany(mappedBy = "productList")
    private List<Order> orderList;

    private Double price;
}
