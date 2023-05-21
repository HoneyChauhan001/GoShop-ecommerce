package com.example.goshop.model;

import com.example.goshop.Enum.CardType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "card")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "card_no",unique = true,nullable = false)
    String cardNo;
    @Column(name = "cvv", unique = true,nullable = false)
    int cvv;
    @Enumerated(EnumType.STRING)
    @Column(name = "card_type",nullable = false)
    CardType cardType;
    @Column(name = "valid_till",nullable = false)
    Date validTill;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    Customer customer;
}
