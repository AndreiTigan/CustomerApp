package com.example.poc.mapper;

import com.example.poc.model.entity.Capital;
import com.example.poc.wsdl_classes.CapitalCityResponse;

public class CapitalMapper {
    public static Capital mapToCapital(CapitalCityResponse cityResponse) {
        Capital capital = new Capital();
        capital.setName(cityResponse.getCapitalCityResult());
        return capital;
    }
}
