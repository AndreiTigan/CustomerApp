package com.example.poc.service.data_fetcher;

import com.example.poc.model.dto.CountryDto;
import com.example.poc.service.CountryService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllCountryDataFetcher implements DataFetcher<List<CountryDto>> {
    private final CountryService countryService;

    public AllCountryDataFetcher(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public List<CountryDto> get(DataFetchingEnvironment environment) throws Exception {
        return countryService.getCountries();
    }
}