package com.example.poc.service;

import com.example.poc.model.dto.*;

import java.util.List;

public interface CountryService {
    CountryNameDto getCountryName(String countryISOCode);

    CapitalDto getCapitalCity(String countryISOCode);

    LanguageDto getLanguageName(String ISOCode);

    List<ContinentDto> getContinents();

    List<CountryDto> getCountries();

    List<CurrencyDto> getCurrencyByCode();
}
