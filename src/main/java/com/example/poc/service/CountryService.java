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
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService extends WebServiceGatewaySupport {


    private final CurrencyMapper currencyMapper;
    private final CountryMapper countryMapper;
    private final ContinentMapper continentMapper;

    @Autowired
    public CountryService(CurrencyMapper currencyMapper, CountryMapper countryMapper, ContinentMapper continentMapper) {
        this.currencyMapper = currencyMapper;
        this.countryMapper = countryMapper;
        this.continentMapper = continentMapper;
    }


    public CountryNameDto getCountryName(String countryISOCode) {
        CountryName countryNameRequest = new CountryName();
        countryNameRequest.setSCountryISOCode(countryISOCode);
        CountryNameDto countryNameDtoResponse = new CountryNameDto();

        ConfigCountryService();

        countryNameDtoResponse.setName(((CountryNameResponse) getWebServiceTemplate().marshalSendAndReceive(countryNameRequest)).getCountryNameResult());
        return countryNameDtoResponse;
    }

    public CapitalDto getCapitalCity(String countryISOCode) {
        CapitalCity capitalCityResponse = new CapitalCity();
        capitalCityResponse.setSCountryISOCode(countryISOCode);
        CapitalDto capitalDtoResponse = new CapitalDto();

        ConfigCountryService();

        capitalDtoResponse.setName(((CapitalCityResponse) getWebServiceTemplate().marshalSendAndReceive(capitalCityResponse)).getCapitalCityResult());
        return capitalDtoResponse;
    }

    public LanguageDto getLanguageName(String ISOCode) {
        LanguageName languageNameRequest = new LanguageName();
        languageNameRequest.setSISOCode(ISOCode);
        LanguageDto languageDtoResponse = new LanguageDto();

        ConfigCountryService();

        languageDtoResponse.setName(((LanguageNameResponse) getWebServiceTemplate().marshalSendAndReceive(languageNameRequest)).getLanguageNameResult());
        return languageDtoResponse;
    }

    public List<ContinentDto> getContinents() {
        ListOfContinentsByName continentsByName = new ListOfContinentsByName();
        ConfigCountryService();
        return ((ListOfContinentsByNameResponse) getWebServiceTemplate().marshalSendAndReceive(continentsByName))
                .getListOfContinentsByNameResult()
                .getTContinent()
                .stream()
                .map(continentMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public List<CountryDto> getCountries() {
        ListOfCountryNamesByName countryNamesByName = new ListOfCountryNamesByName();
        ConfigCountryService();
        return ((ListOfCountryNamesByNameResponse) getWebServiceTemplate().marshalSendAndReceive(countryNamesByName))
                .etListOfCountryNamesByNameResult()
                .getTCountryCodeAndName()
                .stream()
                .map(countryMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public List<CurrencyDto> getCurrencyByCode() {
        ListOfCurrenciesByCode currenciesByCode = new ListOfCurrenciesByCode();
        ConfigCountryService();
        return ((ListOfCurrenciesByCodeResponse) getWebServiceTemplate().marshalSendAndReceive(currenciesByCode))
                .getListOfCurrenciesByCodeResult()
                .getTCurrency()
                .stream()
                .map(currencyMapper::convertToDto)
                .collect(Collectors.toList());
    }

    private void ConfigCountryService() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.example.poc.wsdl_classes");

        getWebServiceTemplate().setDefaultUri("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso");
        getWebServiceTemplate().setMarshaller(marshaller);
        getWebServiceTemplate().setUnmarshaller(marshaller);
    }
}
