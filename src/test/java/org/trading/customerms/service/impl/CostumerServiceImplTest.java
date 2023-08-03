package org.trading.customerms.service.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.trading.customerms.entity.Customer;
import org.trading.customerms.exception.exceptions.InsufficientBalance;
import org.trading.customerms.exception.exceptions.NotFoundException;
import org.trading.customerms.repository.CustomerRepository;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CostumerServiceImplTest {

   @InjectMocks
   CostumerServiceImpl customerService;
   @Mock
   CustomerRepository repository;

   private Customer customer;

   @BeforeEach
   void setCustomer() {
      customer = new Customer();
      customer.setId(1L);
      customer.setFirstName("Test");
      customer.setLastName("Testov");
      customer.setAge(22);
      customer.setBalance(200);
      customer.setAddress("Some Address");
      customer.setPinCode("111111");

   }

   @Test
   void givenGetCustomerByIdWhenFoundThenReturn() {
      //arrange
      Long id = 1L;

      when(repository.findById(id)).thenReturn(Optional.of(customer));

      //act
      Customer response = customerService.getCustomerById(id);

      //assert
      assertNotNull(response);
      assertEquals(customer.getId(), response.getId());
      assertEquals(customer.getFirstName(), response.getFirstName());
      assertEquals(customer.getLastName(), response.getLastName());

   }

   @Test
   void givenGetCustomerByIdWhenNotFoundThenReturn() {
      //arrange
      Long id = 100L;

      when(repository.findById(id)).thenReturn(Optional.empty());

      //act & assert
      Assertions.assertThatThrownBy(() -> customerService.getCustomerById(id))
              .isInstanceOf(NotFoundException.class)
              .hasMessage("Not found customer with such id=" + id);

   }

   @Test
   void givenDecreaseCustomerBalanceWhenDecreasedThenReturnResponseTrue() {
//      arrange
      Long id = 1L;
      double amount = 100;
      when(repository.findById(id)).thenReturn(Optional.of(customer));
//      act
      boolean result = customerService.decreaseBalance(id, amount);
//      assert
      assertTrue(result);
   }

   @Test
   void givenDecreaseCustomerBalanceWhenNotDecreasedThenReturnNotFoundException() {
//      arrange
      Long id = 100L;
      double amount = 100;
      when(repository.findById(id)).thenReturn(Optional.empty());
//      act & assert
      NotFoundException exception = assertThrows(NotFoundException.class, () -> customerService.decreaseBalance(id, amount));
      assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
      assertEquals("Not found customer with such id=" + id, exception.getMessage());
   }

   @Test
   void givenDecreaseCustomerBalanceWhenNotDecreasedThenReturnInsufficientBalance() {
//      arrange
      Long id = 1L;
      double amount = 1000;
      when(repository.findById(id)).thenReturn(Optional.of(customer));
//      act & assert
      InsufficientBalance exception = assertThrows(InsufficientBalance.class, () -> customerService.decreaseBalance(id, amount));
      assertEquals("Insufficient balance", exception.getMessage());
   }

   @Test
   void givenIncreaseCustomerBalanceWhenDecreasedThenReturnResponseTrue() {
//      arrange
      Long id = 1L;
      double amount = 100;
      when(repository.findById(id)).thenReturn(Optional.of(customer));
//      act
      boolean result = customerService.increaseBalance(id, amount);
//      assert
      assertTrue(result);
   }

   @Test
   void givenIncreaseCustomerBalanceWhenNotDecreasedThenReturnNotFoundException() {
//      arrange
      Long id = 100L;
      double amount = 100;
      when(repository.findById(id)).thenReturn(Optional.empty());
//      act & assert
      NotFoundException exception = assertThrows(NotFoundException.class, () -> customerService.increaseBalance(id, amount));
      assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
      assertEquals("Not found customer with such id=" + id, exception.getMessage());
   }


}