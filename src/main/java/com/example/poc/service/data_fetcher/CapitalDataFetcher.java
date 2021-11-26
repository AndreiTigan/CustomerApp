package com.example.poc.service.data_fetcher;

import com.example.poc.model.dto.CapitalDto;
import com.example.poc.service.CountryService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

@Component
public class CapitalDataFetcher implements DataFetcher<CapitalDto> {

    private final CountryService countryService;

    public CapitalDataFetcher(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public CapitalDto get(DataFetchingEnvironment environment) throws Exception {
        String code = environment.getArgument("code");
        return countryService.getCapitalCity(code);
    }
}