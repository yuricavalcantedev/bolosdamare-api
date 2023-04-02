package com.yuri.development.bolos.mare.model;

import com.yuri.development.bolos.mare.enums.EOrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private EOrderStatus eOrderStatus;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "orders_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> productsList;

    @NotNull
    private BigDecimal totalAmount;

    private String note;

    @PrePersist
    public void prePersist(){

        this.createdDate = LocalDateTime.now(ZoneOffset.UTC);
        this.updatedDate = LocalDateTime.now(ZoneOffset.UTC);
        this.eOrderStatus = EOrderStatus.CREATED;

        totalAmount = productsList.stream()
                                  .map(Product::getPrice)
                                  .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedDate = LocalDateTime.now(ZoneOffset.UTC);

        List<BigDecimal> sumOfProductAmount = productsList.stream()
                .map(Product::getPrice).toList();

        totalAmount = productsList.stream()
                                  .map(Product::getPrice)
                                  .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
