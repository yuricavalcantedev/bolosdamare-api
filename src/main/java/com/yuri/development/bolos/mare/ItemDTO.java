package com.yuri.development.bolos.mare;

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

    private String description;
    private String hexColor;

    @NotNull
    private Integer quantity;

    @NotNull
    private ESupplyType supplyType;
}
