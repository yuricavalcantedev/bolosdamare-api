package com.yuri.development.bolos.mare.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Entity
@Table(name = "orderTable")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    @ManyToMany
    @JoinTable(
            name = "product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> productList;

    private Double price;

    @PrePersist
    public void prePersist(){
        this.createdDate = LocalDateTime.now(ZoneOffset.UTC);
        this.updatedDate = LocalDateTime.now(ZoneOffset.UTC);
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedDate = LocalDateTime.now(ZoneOffset.UTC);
    }

}
