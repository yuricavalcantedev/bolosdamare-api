package com.yuri.development.bolos.mare.dto;

import com.yuri.development.bolos.mare.enums.EOrderStatus;
import com.yuri.development.bolos.mare.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long id;
    private String note;
    private EOrderStatus status;
    private BigDecimal totalAmount;
    private List<Product> productList;
}
