package com.example.poc.controller;

import com.example.poc.service.GraphQLService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/graphQL/customers")
@RestController
public class CustomerGraphQLController {

    private final GraphQLService graphQLService;

    public CustomerGraphQLController(GraphQLService graphQLService) {
        this.graphQLService = graphQLService;
    }

    @PostMapping
    public ResponseEntity<Object> getAllCustomers(@RequestBody String query) {
        return ResponseEntity.status(HttpStatus.OK).body(graphQLService.getGraphQL().execute(query));
    }
}
