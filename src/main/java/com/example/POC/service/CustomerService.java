package com.example.POC.service;

import com.example.POC.model.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    CustomerDto addNewCustomer(CustomerDto customerDto);

    CustomerDto getCustomerById(Long id);

    List<CustomerDto> getAllCustomers();

    CustomerDto updateCustomer(CustomerDto customerDto);

    void deleteCustomerById(Long id);
}
