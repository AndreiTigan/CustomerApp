package com.example.poc.service;

import com.example.poc.model.dto.CapitalDto;
import com.example.poc.model.dto.ContinentDto;
import com.example.poc.model.dto.CountryDto;
import com.example.poc.model.dto.CountryNameDto;
import com.example.poc.model.dto.CurrencyDto;
import com.example.poc.model.dto.LanguageDto;

import java.util.List;

public interface CountryService {
    CountryNameDto getCountryName(String countryISOCode);

    CapitalDto getCapitalCity(String countryISOCode);

    LanguageDto getLanguageName(String ISOCode);

    List<ContinentDto> getContinents();

    List<CountryDto> getCountries();

    List<CurrencyDto> getCurrencyByCode();
}
