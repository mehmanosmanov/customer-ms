package org.trading.customerms.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.trading.customerms.exception.exceptions.AgeLimitException;
import org.trading.customerms.exception.exceptions.InsufficientBalance;
import org.trading.customerms.exception.exceptions.NotFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


   @ExceptionHandler({
           AgeLimitException.class,
           NotFoundException.class,
           InsufficientBalance.class
   })
   ResponseEntity<ErrorMessage> handleException(Exception ex) {
      log.info(ex.getMessage(), ex);
      ErrorMessage errorMessage = new ErrorMessage();
      errorMessage.setDate(LocalDateTime.now());
      errorMessage.setStatus(getHttpStatus(ex));
      errorMessage.setErrorCode(errorMessage.getStatus().value());
      errorMessage.setErrorMessage(ex.getMessage());
      return ResponseEntity.status(errorMessage.getStatus()).body(errorMessage);
   }

   private HttpStatus getHttpStatus(Exception ex) {
      if (ex instanceof AgeLimitException) {
         return HttpStatus.BAD_REQUEST;
      } else if (ex instanceof NotFoundException) {
         return HttpStatus.NOT_FOUND;
      } else if (ex instanceof InsufficientBalance) {
         return HttpStatus.BAD_REQUEST;
      } else {
         // Handle any other exceptions
         return HttpStatus.INTERNAL_SERVER_ERROR;
      }
   }
}