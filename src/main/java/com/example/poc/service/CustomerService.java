package com.example.poc.service;

import com.example.poc.model.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    CustomerDto addNewCustomer(CustomerDto customerDto);

    CustomerDto getCustomerById(Integer id);

    List<CustomerDto> getAllCustomers();

    CustomerDto updateCustomer(CustomerDto customerDto);

    void deleteCustomerById(Integer id);
}
