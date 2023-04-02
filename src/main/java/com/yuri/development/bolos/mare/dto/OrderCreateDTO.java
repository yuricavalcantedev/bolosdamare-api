package com.yuri.development.bolos.mare.dto;

import com.yuri.development.bolos.mare.model.Product;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateDTO {

    private BigDecimal totalAmount;

    private String note;

    @NotEmpty
    private List<Product> productList;
}
