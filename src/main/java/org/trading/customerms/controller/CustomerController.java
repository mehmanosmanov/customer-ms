package org.trading.customerms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.trading.customerms.dto.request.CustomerRequest;
import org.trading.customerms.dto.response.CustomerResponse;
import org.trading.customerms.service.CustomerService;

/**
 * @author Mehman on 18-07-2023
 * @project product-ms
 */
@RestController
@RequiredArgsConstructor
public class CustomerController {

   private final CustomerService customerService;

   @PostMapping("/")
   public void createCustomer(@RequestBody CustomerRequest request) {
      customerService.createCostumer(request);
   }

   @GetMapping("/{id}")
   public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id) {
      return ResponseEntity.ok(customerService.getCustomerDtoById(id));
   }

   @GetMapping("/decrease/{id}/{amount}")
   public ResponseEntity<Boolean> decreaseBalance(@PathVariable Long id, @PathVariable double amount) {
      return ResponseEntity.ok(customerService.decreaseBalance(id, amount));
   }

   @GetMapping("/increase/{id}/{amount}")
   public ResponseEntity<Boolean> increaseBalance(@PathVariable Long id, @PathVariable double amount) {
      return ResponseEntity.ok(customerService.increaseBalance(id, amount));
   }
}
