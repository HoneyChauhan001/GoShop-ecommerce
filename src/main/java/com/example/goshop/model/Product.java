package com.example.goshop.model;

import com.example.goshop.Enum.Category;
import com.example.goshop.Enum.ProductStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "name")
    String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    Category category;
    @Column(name = "price")
    int price;
    @Column(name = "quantity")
    int quantity;
    @Column(name = "product_status")
    @Enumerated(EnumType.STRING)
    ProductStatus productStatus;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    Seller seller;
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    List<Item> items = new ArrayList<>();

}
