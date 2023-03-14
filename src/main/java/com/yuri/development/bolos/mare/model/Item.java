package com.yuri.development.bolos.mare.model;

import com.yuri.development.bolos.mare.enums.ESupplyType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "supply")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @NotBlank(message = "name can't be empty or null")
    private String name;

    private String description;

    private String hexColor;

    @NotNull
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private ESupplyType supplyType;

    @ManyToMany(mappedBy = "itemList")
    private List<Product> productList;

}
