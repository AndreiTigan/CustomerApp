package com.example.poc.service;

import com.example.poc.model.dto.CustomerDto;
import com.example.poc.model.entity.Customer;
import com.example.poc.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDto addNewCustomer(CustomerDto customerDto) {
        Customer customer = customerDto.convertToEntity();
        return customerRepository.save(customer).convertToDto();
    }

    @Override
    public CustomerDto getCustomerById(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("The customer does not exist in our database"))
                .convertToDto();
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(Customer::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        Customer customer = customerRepository.findById(customerDto.getId())
                .orElseThrow(()->
                        new RuntimeException("The customer does not exist in our database"));
        BeanUtils.copyProperties(customerDto, customer);
        return customerRepository.save(customer).convertToDto();
    }

    @Override
    public void deleteCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()->
                        new RuntimeException("The customer does not exist in our database"));
        customerRepository.deleteById(customer.getId());
    }
}
