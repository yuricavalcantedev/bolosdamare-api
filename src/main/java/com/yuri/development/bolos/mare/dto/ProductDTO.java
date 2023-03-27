package com.yuri.development.bolos.mare.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private BigDecimal price;

    private List<ItemInProductDTO> itemInProductDTOList;
}
