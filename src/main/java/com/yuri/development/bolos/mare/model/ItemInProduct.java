package com.yuri.development.bolos.mare.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "itemInProduct")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class ItemInProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_in_product_id")
    private Long id;

    @NotNull
    private Long itemId;

    private Integer quantity;

    @NotNull
    private BigDecimal price;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    public ItemInProduct(Long itemId, Integer quantity, BigDecimal price){

        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
    }
}
