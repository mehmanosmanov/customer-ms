package org.trading.customerms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.trading.customerms.entity.Customer;

/**
 * @author Mehman on 18-07-2023
 * @project product-ms
 */
public interface CustomerRepository extends JpaRepository<Customer,Long> {


}
