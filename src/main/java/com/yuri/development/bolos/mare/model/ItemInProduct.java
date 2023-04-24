package com.yuri.development.bolos.mare.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "items_in_products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemInProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name="item_id", nullable=false)
    private Item item;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    private Product product;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Integer quantity;
}
