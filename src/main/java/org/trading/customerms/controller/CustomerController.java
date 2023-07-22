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
@RequestMapping("/make")
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

   @PutMapping("/decrease")
   public boolean decreaseBalance(@RequestParam Long id, @RequestParam double amount) {
      return customerService.decreaseBalance(id, amount);
   }

   @PutMapping("/increase")
   public boolean increaseBalance(@RequestParam Long id, @RequestParam double amount) {
      return customerService.decreaseBalance(id, amount);
   }
}
