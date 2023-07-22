package org.trading.customerms.service;

import org.springframework.stereotype.Service;
import org.trading.customerms.dto.request.CustomerRequest;
import org.trading.customerms.dto.response.CustomerResponse;
import org.trading.customerms.entity.Customer;

/**
 * @author Mehman on 18-07-2023
 * @project product-ms
 */
@Service
public interface CustomerService {
    void createCostumer(CustomerRequest request);
    Customer getCustomerById(Long id);
    CustomerResponse getCustomerDtoById(Long id);


    boolean decreaseBalance(Long id, double amount);
    boolean increaseBalance(Long id, double amount);

}
