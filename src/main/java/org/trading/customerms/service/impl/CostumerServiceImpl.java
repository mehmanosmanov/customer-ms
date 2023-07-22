package org.trading.customerms.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.trading.customerms.dto.request.CustomerRequest;
import org.trading.customerms.dto.response.CustomerResponse;
import org.trading.customerms.entity.Customer;
import org.trading.customerms.exception.exceptions.InsufficientBalance;
import org.trading.customerms.exception.exceptions.NotFoundException;
import org.trading.customerms.mapper.CustomerMapper;
import org.trading.customerms.repository.CustomerRepository;
import org.trading.customerms.service.CustomerService;

/**
 * @author Mehman on 18-07-2023
 * @project customer-ms
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CostumerServiceImpl implements CustomerService {

   private final CustomerRepository repository;
   private final CustomerMapper mapper;

   @Override
   public void createCostumer(CustomerRequest request) {
      log.info("Creating a new customer");
      repository.save(mapper.dtoToEntity(request));
   }

   @Override
   public Customer getCustomerById(Long id) {
      log.info("Getting customer by id={}", id);
      return repository.findById(id).orElseThrow(
              () -> new NotFoundException("Not found customer with such id=" + id));
   }
   @Override
   public CustomerResponse getCustomerDtoById(Long id) {
      return mapper.entityToDto(getCustomerById(id));
   }

   @Override
   public boolean decreaseBalance(Long id, double amount) {
      log.info("Start transaction");
      Customer customer = getCustomerById(id);
      if (customer.getBalance() < amount) {
         log.error("Insufficient balance");
         throw new InsufficientBalance();
      }
      var balance = customer.getBalance() - amount;
      customer.setBalance(balance);
      repository.save(customer);
      log.info("Transaction was successful");
      return true;
   }

   @Override
   public boolean increaseBalance(Long id, double amount) {
      log.info("Start transaction");
      Customer customer = repository.findById(id).orElseThrow(
              () -> new NotFoundException("Not found customer with such id=" + id));
      var balance = customer.getBalance() + amount;
      customer.setBalance(balance);
      repository.save(customer);
      log.info("Transaction was successful");
      return true;
   }
}
