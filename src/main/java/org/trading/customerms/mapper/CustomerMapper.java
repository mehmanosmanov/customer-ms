package org.trading.customerms.mapper;

import org.mapstruct.Mapper;
import org.trading.customerms.dto.request.CustomerRequest;
import org.trading.customerms.dto.response.CustomerResponse;
import org.trading.customerms.entity.Customer;

/**
 * @author Mehman on 18-07-2023
 * @project product-ms
 */
@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerResponse entityToDto(Customer customer);

    Customer dtoToEntity(CustomerRequest request);
}
