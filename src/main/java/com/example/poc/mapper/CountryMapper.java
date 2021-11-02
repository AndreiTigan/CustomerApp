package com.example.poc.mapper;

import com.example.poc.model.entity.Country;
import com.example.poc.wsdl_classes.ListOfCountryNamesByNameResponse;
import com.example.poc.wsdl_classes.TCountryCodeAndName;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CountryMapper {
    public static Country mapToCountry(TCountryCodeAndName codeAndName) {
        Country country = new Country();
        country.setName(codeAndName.getSName());
        country.setCode(codeAndName.getSISOCode());
        return country;
    }

    public static TCountryCodeAndName mapToTCountryCodeAndName(Country country) {
        TCountryCodeAndName codeAndName = new TCountryCodeAndName();
        codeAndName.setSName(country.getName());
        codeAndName.setSISOCode(country.getCode());
        return codeAndName;
    }

    public static List<Country> mapToCountryList(ListOfCountryNamesByNameResponse list) {
        return list.etListOfCountryNamesByNameResult().getTCountryCodeAndName()
                .stream()
                .map(CountryMapper::mapToCountry)
                .collect(Collectors.toList());
    }
}
