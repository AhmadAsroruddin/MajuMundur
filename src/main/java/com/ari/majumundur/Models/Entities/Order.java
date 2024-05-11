package com.ari.majumundur.Models.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "m_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customerId;

    @OneToMany(mappedBy = "order" , cascade = CascadeType.PERSIST)
    private List<OrderDetail> orderDetails;
}
