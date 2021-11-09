package com.example.poc.service;

import com.example.poc.mapper.ContinentMapper;
import com.example.poc.mapper.CountryMapper;
import com.example.poc.mapper.CurrencyMapper;
import com.example.poc.model.dto.CapitalDto;
import com.example.poc.model.dto.ContinentDto;
import com.example.poc.model.dto.CountryDto;
import com.example.poc.model.dto.CountryNameDto;
import com.example.poc.model.dto.CurrencyDto;
import com.example.poc.model.dto.LanguageDto;
import com.example.poc.wsdl_classes.CapitalCity;
import com.example.poc.wsdl_classes.CapitalCityResponse;
import com.example.poc.wsdl_classes.CountryName;
import com.example.poc.wsdl_classes.CountryNameResponse;
import com.example.poc.wsdl_classes.LanguageName;
import com.example.poc.wsdl_classes.LanguageNameResponse;
import com.example.poc.wsdl_classes.ListOfContinentsByNameResponse;
import com.example.poc.wsdl_classes.ListOfCountryNamesByNameResponse;
import com.example.poc.wsdl_classes.ListOfCountryNamesByName;
import com.example.poc.wsdl_classes.ListOfContinentsByName;
import com.example.poc.wsdl_classes.ListOfCurrenciesByCode;
import com.example.poc.wsdl_classes.ListOfCurrenciesByCodeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import java.util.List;

public class CountryService extends WebServiceGatewaySupport {

    @Autowired
    private CurrencyMapper currencyMapper;
    @Autowired
    private CountryMapper countryMapper;
    @Autowired
    private ContinentMapper continentMapper;


    public CountryNameDto getCountryName(String countryISOCode) {
        CountryName countryNameRequest = new CountryName();
        countryNameRequest.setSCountryISOCode(countryISOCode);
        CountryNameDto countryNameDtoResponse = new CountryNameDto();
        countryNameDtoResponse.setName(((CountryNameResponse) getWebServiceTemplate().marshalSendAndReceive(countryNameRequest)).getCountryNameResult());
        return countryNameDtoResponse;
    }

    public CapitalDto getCapitalCity(String countryISOCode) {
        CapitalCity capitalCityResponse = new CapitalCity();
        capitalCityResponse.setSCountryISOCode(countryISOCode);
        CapitalDto capitalDtoResponse = new CapitalDto();
        capitalDtoResponse.setName(((CapitalCityResponse) getWebServiceTemplate().marshalSendAndReceive(capitalCityResponse)).getCapitalCityResult());
        return capitalDtoResponse;
    }

    public LanguageDto getLanguageName(String ISOCode) {
        LanguageName languageNameRequest = new LanguageName();
        languageNameRequest.setSISOCode(ISOCode);
        LanguageDto languageDtoResponse = new LanguageDto();
        languageDtoResponse.setName(((LanguageNameResponse) getWebServiceTemplate().marshalSendAndReceive(languageNameRequest)).getLanguageNameResult());
        return languageDtoResponse;
    }

    public List<ContinentDto> getContinents() {
        ListOfContinentsByName continentsByName = new ListOfContinentsByName();
        return continentMapper.convertToDtoList((ListOfContinentsByNameResponse) getWebServiceTemplate().marshalSendAndReceive(continentsByName));
    }

    public List<CountryDto> getCountries() {
        ListOfCountryNamesByName countryNamesByName = new ListOfCountryNamesByName();
        return countryMapper.convertToDtoList((ListOfCountryNamesByNameResponse) getWebServiceTemplate().marshalSendAndReceive(countryNamesByName));
    }

    public List<CurrencyDto> getCurrencyByCode() {
        ListOfCurrenciesByCode currenciesByCode = new ListOfCurrenciesByCode();
        return currencyMapper.convertToDtoList((ListOfCurrenciesByCodeResponse) getWebServiceTemplate().marshalSendAndReceive(currenciesByCode));
    }
}
