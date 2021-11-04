package com.example.poc.service.data_fetcher;

import com.example.poc.model.entity.Country;
import com.example.poc.service.CountryService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllCountryDataFetcher implements DataFetcher<List<Country>> {
    private final CountryService countryService;

    public AllCountryDataFetcher(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public List<Country> get(DataFetchingEnvironment environment) throws Exception {
        return countryService.getCountries();
    }
}
