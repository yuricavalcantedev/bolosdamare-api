package com.yuri.development.bolos.mare.dto;

import com.yuri.development.bolos.mare.model.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemInProductDTO {

    private Long id;
    private Item item;
    private Integer quantity;
    private BigDecimal price;
}
