package com.example.poc.service.data_fetcher;

import com.example.poc.model.entity.Customer;
import com.example.poc.repository.CustomerRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllCustomersDataFetcher implements DataFetcher<List<Customer>> {

    private final CustomerRepository customerRepository;

    public AllCustomersDataFetcher(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> get(DataFetchingEnvironment environment) throws Exception {
        return customerRepository.findAll();
    }
}