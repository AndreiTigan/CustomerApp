package com.example.poc.service;

import com.example.poc.model.entity.Capital;
import com.example.poc.model.entity.Continent;
import com.example.poc.model.entity.Country;
import com.example.poc.model.entity.CountryNameEntity;
import com.example.poc.model.entity.Currency;
import com.example.poc.model.entity.Language;
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
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import java.util.List;

import static com.example.poc.mapper.CapitalMapper.mapToCapital;
import static com.example.poc.mapper.ContinentMapper.mapToContinentList;
import static com.example.poc.mapper.CountryMapper.mapToCountryList;
import static com.example.poc.mapper.CountryNameMapper.mapToCountryName;
import static com.example.poc.mapper.CurrencyMapper.mapToCurrencyList;
import static com.example.poc.mapper.LanguageMapper.mapToLanguage;


public class CountryService extends WebServiceGatewaySupport {

    public CountryNameEntity getCountryName(String countryISOCode) {
        CountryName countryName = new CountryName();
        countryName.setSCountryISOCode(countryISOCode);
        return mapToCountryName((CountryNameResponse) getWebServiceTemplate().marshalSendAndReceive(countryName));
    }

    public Capital getCapitalCity(String countryISOCode) {
        CapitalCity capitalCity = new CapitalCity();
        capitalCity.setSCountryISOCode(countryISOCode);
        return mapToCapital((CapitalCityResponse) getWebServiceTemplate().marshalSendAndReceive(capitalCity));
    }

    public Language getLanguageName(String ISOCode) {
        LanguageName languageName = new LanguageName();
        languageName.setSISOCode(ISOCode);
        return mapToLanguage((LanguageNameResponse) getWebServiceTemplate().marshalSendAndReceive(languageName));
    }

    public List<Continent> getContinents() {
        ListOfContinentsByName continentsByName = new ListOfContinentsByName();
        return mapToContinentList((ListOfContinentsByNameResponse) getWebServiceTemplate().marshalSendAndReceive(continentsByName));
    }

    public List<Country> getCountries() {
        ListOfCountryNamesByName countryNamesByName = new ListOfCountryNamesByName();
        return mapToCountryList((ListOfCountryNamesByNameResponse) getWebServiceTemplate().marshalSendAndReceive(countryNamesByName));
    }

    public List<Currency> getCurrencyByCode() {
        ListOfCurrenciesByCode currenciesByCode = new ListOfCurrenciesByCode();
        return mapToCurrencyList((ListOfCurrenciesByCodeResponse) getWebServiceTemplate().marshalSendAndReceive(currenciesByCode));
    }
}
