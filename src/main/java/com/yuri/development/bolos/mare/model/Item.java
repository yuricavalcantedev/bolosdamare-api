package com.yuri.development.bolos.mare.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yuri.development.bolos.mare.enums.ESupplyType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name can't be empty or null")
    private String name;

    @NotNull
    private Integer quantity;

    @NotNull
    private BigDecimal price;

    private String hexColor;

    @Enumerated(EnumType.STRING)
    private ESupplyType supplyType;

    @JsonIgnore
    @OneToMany(mappedBy="item", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ItemInProduct> itemInProductList;
}