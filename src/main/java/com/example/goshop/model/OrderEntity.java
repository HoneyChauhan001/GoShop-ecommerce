package com.example.goshop.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_info")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "order_no")
    String orderNo;//UUID
    @Column(name = "total_value")
    int totalValue;
    @Column(name = "order_date")
    @CreationTimestamp
    Date orderDate;
    @Column(name = "card_used")
    String cardUsed;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    Customer customer;
    @OneToMany(mappedBy = "orderEntity",cascade = CascadeType.ALL)
    List<Item> items = new ArrayList<>();

}
