package org.trading.customerms.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Mehman on 18-07-2023
 * @project product-ms
 */

@Setter
@Getter
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pinCode;
    private String firstName;
    private String lastName;
    private int age;
    private String address;
    private double balance;
}
