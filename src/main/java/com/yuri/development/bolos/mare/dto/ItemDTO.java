package com.yuri.development.bolos.mare.dto;

import com.yuri.development.bolos.mare.enums.ESupplyType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    @NotNull
    private String name;

    private String hexColor;

    @NotNull
    private Integer priority;

    @NotNull
    private Integer quantity;

    @NotNull
    private Double price;

    @NotNull
    private ESupplyType supplyType;
}
