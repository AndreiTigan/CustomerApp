package com.example.poc.service;

import com.example.poc.model.dto.CustomerDto;
import com.example.poc.model.entity.Customer;
import com.example.poc.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.poc.mapper.CustomerMapper.toCustomerDto;
import static com.example.poc.mapper.CustomerMapper.toCustomerDtoList;
import static com.example.poc.mapper.CustomerMapper.toCustomerEntity;

@Service
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDto addNewCustomer(CustomerDto customerDto) {
        Customer customer = toCustomerEntity(customerDto);
        return toCustomerDto(customerRepository.save(customer));
    }

    @Override
    public CustomerDto getCustomerById(Integer id) {
        return toCustomerDto(customerRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("The customer does not exist in our database")));
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return toCustomerDtoList(customerRepository.findAll());
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        Customer customer = customerRepository.findById(customerDto.getId())
                .orElseThrow(()->
                        new RuntimeException("The customer does not exist in our database"));
        BeanUtils.copyProperties(customerDto, customer);
        return toCustomerDto(customerRepository.save(customer));
    }

    @Override
    public void deleteCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()->
                        new RuntimeException("The customer does not exist in our database"));
        customerRepository.deleteById(customer.getId());
    }
}
