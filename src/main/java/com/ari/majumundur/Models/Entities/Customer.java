package com.ari.majumundur.Models.Entities;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "m_customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "point")
    private Integer points;

    @Column(name = "mobile_phone")
    private String mobilePhone;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;


    @OneToOne
    @JoinColumn(name = "user_credential_id")
    private UserCredential userCredential;
}
