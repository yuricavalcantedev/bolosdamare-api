package com.yuri.development.bolos.mare.dto;

import com.yuri.development.bolos.mare.model.Item;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateDTO {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private BigDecimal price;

    private List<Item> itemsList;
}
