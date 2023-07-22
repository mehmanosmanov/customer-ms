package org.trading.customerms.exception.exceptions;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * @author Mehman on 18-07-2023
 * @project customer-ms
 */
public class InsufficientBalance extends RuntimeException {
   public InsufficientBalance() {
      super("Insufficient balance");
   }
}
