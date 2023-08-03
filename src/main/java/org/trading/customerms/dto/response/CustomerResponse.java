package org.trading.customerms.dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Mehman on 18-07-2023
 * @project customer-ms
 */
@Setter
@Getter
public class CustomerResponse {

    private String firstName;
    private String lastName;
    private int age;
    private String address;
    private double balance;
}
