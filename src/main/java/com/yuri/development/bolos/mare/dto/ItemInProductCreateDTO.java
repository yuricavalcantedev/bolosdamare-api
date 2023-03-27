package com.yuri.development.bolos.mare.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemInProductCreateDTO {

    @NotNull
    private Long itemId;

    @NotNull
    private Integer quantity;

    @NotNull
    private BigDecimal price;
}
