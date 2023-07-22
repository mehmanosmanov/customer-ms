package org.trading.customerms.dto.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Mehman on 18-07-2023
 * @project customer-ms
 */
@Setter
@Getter
public class CustomerRequest {

    private String pinCode;
    private String firstNAme;
    private String lastName;
    private int age;
    private String address;
    private double balance;
}
