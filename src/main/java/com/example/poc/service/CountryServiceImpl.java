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
import com.example.poc.soap.SoapRequest;
import com.example.poc.wsdl_classes.CapitalCity;
import com.example.poc.wsdl_classes.CountryName;
import com.example.poc.wsdl_classes.LanguageName;
import com.example.poc.wsdl_classes.ListOfCountryNamesByName;
import com.example.poc.wsdl_classes.ListOfContinentsByName;
import com.example.poc.wsdl_classes.ListOfCurrenciesByCode;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.transport.http.ClientHttpRequestMessageSender;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {


    private final CurrencyMapper currencyMapper;
    private final CountryMapper countryMapper;
    private final ContinentMapper continentMapper;
    private final SoapRequest soapRequest;


    public CountryServiceImpl(CurrencyMapper currencyMapper, CountryMapper countryMapper, ContinentMapper continentMapper, SoapRequest soapRequest) {
        this.currencyMapper = currencyMapper;
        this.countryMapper = countryMapper;
        this.continentMapper = continentMapper;
        this.soapRequest = soapRequest;
    }


    public CountryNameDto getCountryName(String countryISOCode) {
        CountryName countryNameRequest = new CountryName();
        countryNameRequest.setSCountryISOCode(countryISOCode);
        CountryNameDto countryNameDtoResponse = new CountryNameDto();
        countryNameDtoResponse.setName(
                soapRequest.countryNameSoapResponse(countryNameRequest)
                        .getCountryNameResult());
        return countryNameDtoResponse;
    }

    public CapitalDto getCapitalCity(String countryISOCode) {
        CapitalCity capitalCityResponse = new CapitalCity();
        capitalCityResponse.setSCountryISOCode(countryISOCode);
        CapitalDto capitalDtoResponse = new CapitalDto();
        capitalDtoResponse.setName(
                soapRequest.capitalCitySoapResponse(capitalCityResponse)
                        .getCapitalCityResult());
        return capitalDtoResponse;
    }

    public LanguageDto getLanguageName(String ISOCode) {
        LanguageName languageNameRequest = new LanguageName();
        languageNameRequest.setSISOCode(ISOCode);
        LanguageDto languageDtoResponse = new LanguageDto();
        languageDtoResponse.setName(
                soapRequest.languageNameSoapResponse(languageNameRequest)
                        .getLanguageNameResult());
        return languageDtoResponse;
    }

  public List<ContinentDto> getContinents() {
    ListOfContinentsByName continentsByName = new ListOfContinentsByName();
    return soapRequest
        .listOfContinentsSoapResponse(continentsByName)
        .getListOfContinentsByNameResult()
        .getTContinent()
        .stream()
        .map(continentMapper::convertToDto)
        .collect(Collectors.toList());
    }

    public List<CountryDto> getCountries() {
        ListOfCountryNamesByName countryNamesByName = new ListOfCountryNamesByName();
        return soapRequest
                .listOfCountriesSoapResponse(countryNamesByName)
                .etListOfCountryNamesByNameResult()
                .getTCountryCodeAndName()
                .stream()
                .map(countryMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public List<CurrencyDto> getCurrencyByCode() {
        ListOfCurrenciesByCode currenciesByCode = new ListOfCurrenciesByCode();
        return soapRequest
                .listOfCurrenciesSoapResponse(currenciesByCode)
                .getListOfCurrenciesByCodeResult()
                .getTCurrency()
                .stream()
                .map(currencyMapper::convertToDto)
                .collect(Collectors.toList());
    }
}
