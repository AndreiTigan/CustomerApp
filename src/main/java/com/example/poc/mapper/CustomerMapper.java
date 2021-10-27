package com.example.poc.mapper;

import com.example.poc.model.dto.CustomerDto;
import com.example.poc.model.entity.Customer;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerMapper {
    public static CustomerDto toCustomerDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setDob(customer.getDob());
        customerDto.setCity(customer.getCity());
        customerDto.setHireDate(customer.getHireDate());
        customerDto.setBonus(customer.getBonus());
        return customerDto;
    }

    public static Customer toCustomerEntity(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setDob(customerDto.getDob());
        customer.setCity(customerDto.getCity());
        customer.setHireDate(customerDto.getHireDate());
        customer.setBonus(customerDto.getBonus());
        return customer;
    }

    public static List<CustomerDto> toCustomerDtoList(List<Customer> customers) {
        if (CollectionUtils.isEmpty(customers)) return Collections.emptyList();
        return customers.stream().map(CustomerMapper::toCustomerDto).collect(Collectors.toList());
    }
}
