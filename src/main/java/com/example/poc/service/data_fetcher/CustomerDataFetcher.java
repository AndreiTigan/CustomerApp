package com.example.poc.service.data_fetcher;

import com.example.poc.model.entity.Customer;
import com.example.poc.repository.CustomerRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataFetcher implements DataFetcher<Customer> {

    private final CustomerRepository customerRepository;

    public CustomerDataFetcher(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer get(DataFetchingEnvironment environment) throws Exception {
        Integer id = environment.getArgument("id");
        return customerRepository.getById(id);
    }
}
