package com.example.poc.service;

import com.example.poc.mapper.CustomerMapper;
import com.example.poc.model.dto.CustomerDto;
import com.example.poc.model.entity.Customer;
import com.example.poc.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

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
                        new EntityNotFoundException("The customer does not exist in our database")));
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        if (customers.isEmpty()){
            throw  new NullPointerException("Null objects cannot be mapped");
        }
        else
            return customers
                    .stream()
                    .map(customerMapper::convertToDto)
                    .collect(Collectors.toList());
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        Customer customer = customerRepository.findById(customerDto.getId())
                .orElseThrow(()->
                        new EntityNotFoundException("The customer does not exist in our database"));
        BeanUtils.copyProperties(customerDto, customer);
        return customerMapper.convertToDto(customerRepository.save(customer));
    }

    @Override
    public void deleteCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()->
                        new EntityNotFoundException("The customer does not exist in our database"));
        customerRepository.deleteById(customer.getId());
    }
}
