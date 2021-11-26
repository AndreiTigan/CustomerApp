package com.example.poc.service;

import com.example.poc.service.data_fetcher.AllCountryDataFetcher;
import com.example.poc.service.data_fetcher.AllCustomersDataFetcher;
import com.example.poc.service.data_fetcher.CapitalDataFetcher;
import com.example.poc.service.data_fetcher.CustomerDataFetcher;
import graphql.GraphQL;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
public class GraphQLService {

    @Value("classpath:schema.graphql")
    Resource resource;

    private  GraphQL graphQL;

    private final AllCustomersDataFetcher allCustomersDataFetcher;
    private final CustomerDataFetcher customerDataFetcher;
    private final AllCountryDataFetcher allCountryDataFetcher;
    private final CapitalDataFetcher capitalDataFetcher;

    @Autowired
    public GraphQLService(AllCustomersDataFetcher allCustomersDataFetcher, CustomerDataFetcher customerDataFetcher, AllCountryDataFetcher allCountryDataFetcher, CapitalDataFetcher capitalDataFetcher) {
        this.allCustomersDataFetcher = allCustomersDataFetcher;
        this.customerDataFetcher = customerDataFetcher;
        this.allCountryDataFetcher = allCountryDataFetcher;
        this.capitalDataFetcher = capitalDataFetcher;
    }

    @PostConstruct
    private void loadSchema() throws IOException {
        //get the schema
        File schemaFile = resource.getFile();
        //parse schema
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        //wire or map the schema to query
        RuntimeWiring wiring = buildRuntimeWiring();
        //make the schema executable
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry,wiring);
        //build schema using GraphQL API
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allCustomers", allCustomersDataFetcher)
                        .dataFetcher("customer", customerDataFetcher)
                        .dataFetcher("allCountry", allCountryDataFetcher)
                        .dataFetcher("capital", capitalDataFetcher))
                .scalar(ExtendedScalars.Date)
                .build();
    }

    //expose the service to be accessed from controller
    @Bean
    public GraphQL getGraphQL() {
        return graphQL;
    }
}