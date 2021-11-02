package com.example.poc.mapper;

import com.example.poc.model.entity.CountryNameEntity;
import com.example.poc.wsdl_classes.CountryNameResponse;

public class CountryNameMapper {
    public static CountryNameEntity mapToCountryName(CountryNameResponse countryNameResponse) {
        CountryNameEntity countryName = new CountryNameEntity();
        countryName.setName(countryNameResponse.getCountryNameResult());
        return countryName;
    }
}
