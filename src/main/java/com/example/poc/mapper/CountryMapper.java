package com.example.poc.mapper;

import com.example.poc.model.dto.CountryDto;
import com.example.poc.wsdl_classes.ListOfCountryNamesByNameResponse;
import com.example.poc.wsdl_classes.TCountryCodeAndName;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CountryMapper implements Mapper<TCountryCodeAndName, CountryDto, ListOfCountryNamesByNameResponse> {

    @Override
    public CountryDto convertToDto(TCountryCodeAndName codeAndName) {
        CountryDto countryDto = new CountryDto();
        countryDto.setName(codeAndName.getSName());
        countryDto.setCode(codeAndName.getSISOCode());
        return countryDto;
    }

    @Override
    public TCountryCodeAndName convertToEntity(CountryDto countryDto) {
        TCountryCodeAndName codeAndName = new TCountryCodeAndName();
        codeAndName.setSName(countryDto.getName());
        codeAndName.setSISOCode(countryDto.getCode());
        return codeAndName;
    }

    @Override
    public List<CountryDto> convertToDtoList(ListOfCountryNamesByNameResponse list) {
        return list.etListOfCountryNamesByNameResult().getTCountryCodeAndName()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
