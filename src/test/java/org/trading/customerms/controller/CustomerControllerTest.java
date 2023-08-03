package org.trading.customerms.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.trading.customerms.CustomerMsApplication;
import org.trading.customerms.dto.response.CustomerResponse;
import org.trading.customerms.exception.ErrorResponse;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Mehman Osmanov
 * @project customer-ms
 * @created 14:58 Tuesday 01-08-2023
 */
@SpringBootTest(classes = CustomerMsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
class CustomerControllerTest {
   @LocalServerPort
   private int serverPort;

   private String url;

   @BeforeEach
   void setUp() {
      url = "http://localhost:" + serverPort + "/customer";
   }

   @Autowired
   private TestRestTemplate restTemplate;


   @Test
   @Sql(scripts = "classpath:sql/customer.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
   public void givenGetCustomerByIdWhenFoundThenReturnResultWith200() {
      //arrange
      Long id = 1L;
      //act
      ResponseEntity<CustomerResponse> response = restTemplate.getForEntity(url + "/" + id, CustomerResponse.class);
      //assert
      assertNotNull(response.getBody());
      assertEquals(HttpStatus.OK, response.getStatusCode());

      final CustomerResponse customer = response.getBody();
      assertEquals(3000, customer.getBalance());
      assertEquals("Test", customer.getFirstName());
      assertEquals("Testov", customer.getLastName());
      assertEquals("Baku", customer.getAddress());
      assertEquals(25, customer.getAge());
   }

   @Test
   @Sql(scripts = "classpath:sql/customer.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
   public void givenGetCustomerByIdWhenFoundThenReturnNotFoundException() {
      //arrange
      Long id = 100L;
      //act & assert
      ResponseEntity<ErrorResponse> response = restTemplate.getForEntity(url + "/" + id, ErrorResponse.class);
      assertNotNull(response.getBody());
      assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
   }


   @Test
   @Sql(scripts = "classpath:sql/customer.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
   public void givenDecreaseBalanceWhenDecreasedThenReturnTrue() {
//      arrange
      Long id = 1L;
      double amount = 100;
//      act
      ResponseEntity<Boolean> result = restTemplate.getForEntity(url + "/decrease/" + id+"/"+amount, Boolean.class);
//      assert
      assertNotNull(result.getBody());
      assertTrue(result.getBody());
   }



   @Test
   @Sql(scripts = "classpath:sql/customer.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
   public void givenDecreaseBalanceWhenNotDecreasedThenReturnNotFoundException() {
//      arrange
      Long id = 100L;
      double amount = 100;
//      act
      ResponseEntity<ErrorResponse> result = restTemplate.getForEntity(url + "/decrease/" + id+"/"+amount, ErrorResponse.class);
//      assert
      assertNotNull(result.getBody());
      assertEquals(HttpStatus.NOT_FOUND,result.getBody().getStatus());
   }

   @Test
   @Sql(scripts = "classpath:sql/customer.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
   public void givenDecreaseBalanceWhenNotDecreasedThenReturnInsufficientBalanceException() {
//      arrange
      Long id = 1L;
      double amount = 1000;
//      act
      ResponseEntity<ErrorResponse> result = restTemplate.getForEntity(url + "/decrease/" + id+"/"+amount, ErrorResponse.class);
//      assert
      assertNotNull(result.getBody());
      assertEquals("Insufficient balance",result.getBody().getErrorMessage());
   }

}