package org.trading.customerms.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.trading.customerms.entity.Customer;

import javax.crypto.spec.OAEPParameterSpec;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
@EnableConfigurationProperties
@EnableJpaRepositories
class CustomerRepositoryTest {

   @Autowired
   private CustomerRepository customerRepository;

   @Test
   @Sql(scripts = "classpath:sql/customer.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
   public void givenFindCustomerByIdWhenFoundThenReturnEntity(){
      //arrange
      Long id = 1l;

      //act
      Optional<Customer> result = customerRepository.findById(id);

      //assert
      assertFalse(result.isEmpty());
      Customer customer = result.get();
      assertEquals(id,customer.getId());
      assertEquals(25,customer.getAge());
      assertEquals("Test",customer.getFirstName());
      assertEquals("Testov",customer.getLastName());

   }

}