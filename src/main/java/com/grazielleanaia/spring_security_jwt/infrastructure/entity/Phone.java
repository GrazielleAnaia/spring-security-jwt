package com.grazielleanaia.spring_security_jwt.infrastructure.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "phone")
@Builder


public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private String number;

    @Column(name = "code")
    private String code;

    @Column(name = "customer_id")
    private Long customer_id;


}
