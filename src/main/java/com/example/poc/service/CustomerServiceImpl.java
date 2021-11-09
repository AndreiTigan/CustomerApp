package com.example.poc.service;

import com.example.poc.mapper.CustomerMapper;
import com.example.poc.model.dto.CustomerDto;
import com.example.poc.model.entity.Customer;
import com.example.poc.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDto addNewCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.convertToEntity(customerDto);
        return customerMapper.convertToDto(customerRepository.save(customer));
    }

    @Override
    public CustomerDto getCustomerById(Integer id) {
        return customerMapper.convertToDto(customerRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("The customer does not exist in our database")));
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerMapper.convertToDtoList(customerRepository.findAll());
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        Customer customer = customerRepository.findById(customerDto.getId())
                .orElseThrow(()->
                        new RuntimeException("The customer does not exist in our database"));
        BeanUtils.copyProperties(customerDto, customer);
        return customerMapper.convertToDto(customerRepository.save(customer));
    }

    @Override
    public void deleteCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()->
                        new RuntimeException("The customer does not exist in our database"));
        customerRepository.deleteById(customer.getId());
    }
}
