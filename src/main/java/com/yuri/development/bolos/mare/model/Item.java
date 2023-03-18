package com.yuri.development.bolos.mare.model;

import com.yuri.development.bolos.mare.enums.ESupplyType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "item")
@Data
@AllArgsConstructor
@NoArgsConstructor
//TODO maybe I should create another Item class(ItemInProduct), a composition one, to put inside Product
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @NotBlank(message = "name can't be empty or null")
    private String name;

    @NotNull
    private Integer priority;

    @NotNull
    private Integer quantity;

    @NotNull
    private Double price;

    private String hexColor;

    @Enumerated(EnumType.STRING)
    private ESupplyType supplyType;

}