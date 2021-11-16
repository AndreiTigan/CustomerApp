package com.example.poc.utils;

import com.example.poc.model.dto.CountryDto;
import com.example.poc.model.dto.ContinentDto;
import com.example.poc.model.dto.CurrencyDto;
import com.example.poc.model.dto.CustomerDto;
import com.example.poc.model.entity.Customer;
import com.example.poc.wsdl_classes.ArrayOftContinent;
import com.example.poc.wsdl_classes.ArrayOftCountryCodeAndName;
import com.example.poc.wsdl_classes.ArrayOftCurrency;
import com.example.poc.wsdl_classes.CapitalCityResponse;
import com.example.poc.wsdl_classes.CountryNameResponse;
import com.example.poc.wsdl_classes.LanguageNameResponse;
import com.example.poc.wsdl_classes.ListOfContinentsByNameResponse;
import com.example.poc.wsdl_classes.ListOfCountryNamesByNameResponse;
import com.example.poc.wsdl_classes.ListOfCurrenciesByCodeResponse;
import com.example.poc.wsdl_classes.TContinent;
import com.example.poc.wsdl_classes.TCountryCodeAndName;
import com.example.poc.wsdl_classes.TCurrency;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class TestUtils {

    public static Customer testCustomerTemplate(Integer id, String firstName, String lastName, String email,
                                                LocalDate dob, String city, LocalDate hireDate, Double bonus) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setDob(dob);
        customer.setCity(city);
        customer.setHireDate(hireDate);
        customer.setBonus(bonus);
        return customer;
    }

    public static CustomerDto testCustomerDtoTemplate(Integer id, String firstName, String lastName, String email,
                                                      LocalDate dob, String city, LocalDate hireDate, Double bonus) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(id);
        customerDto.setFirstName(firstName);
        customerDto.setLastName(lastName);
        customerDto.setEmail(email);
        customerDto.setDob(dob);
        customerDto.setCity(city);
        customerDto.setHireDate(hireDate);
        customerDto.setBonus(bonus);
        return customerDto;
    }

    public static Customer createTestCustomer() {
        return testCustomerTemplate(2,"Andrei", "Tigan", "andrei.tigan@accesa.eu", LocalDate.parse("1999-01-06"),
                "Oradea", LocalDate.parse("2021-04-03"), 300d);
    }

    public static CustomerDto createTestCustomerDTO() {

        return testCustomerDtoTemplate(2,"Andrei", "Tigan", "andrei.tigan@accesa.eu", LocalDate.parse("1999-01-06"),
                "Oradea", LocalDate.parse("2021-04-03"), 300d);
    }

    public static List<Customer> testCustomerList() {
        return Arrays.asList(
                testCustomerTemplate(2,"Andrei", "Tigan", "andrei.tigan@accesa.eu", LocalDate.parse("1999-01-06"), "Oradea", LocalDate.parse("2021-04-03"), 300d),
                testCustomerTemplate(3,"Ion", "Popescu", "ion.popescu@email.com", LocalDate.parse("1995-12-25"), "Cluj", LocalDate.parse("2020-10-01"), 300d),
                testCustomerTemplate(4,"Maria", "Lungu", "maria.lungu@email.com", LocalDate.parse("1994-04-14"), "Bucuresti", LocalDate.parse("2017-03-03"), 300d)
        );
    }

    public static List<CustomerDto> testCustomerDtoList() {
        return Arrays.asList(
                testCustomerDtoTemplate(2,"Andrei", "Tigan", "andrei.tigan@accesa.eu", LocalDate.parse("1999-01-06"), "Oradea", LocalDate.parse("2021-04-03"), 300d),
                testCustomerDtoTemplate(3,"Ion", "Popescu", "ion.popescu@email.com", LocalDate.parse("1995-12-25"), "Cluj", LocalDate.parse("2020-10-01"), 300d),
                testCustomerDtoTemplate(4,"Maria", "Lungu", "maria.lungu@email.com", LocalDate.parse("1994-04-14"), "Bucuresti", LocalDate.parse("2017-03-03"), 300d)
        );
    }


    public static CountryNameResponse testCountryNameTemplate(String country) {
        CountryNameResponse countryNameResponse = new CountryNameResponse();
        countryNameResponse.setCountryNameResult(country);
        return  countryNameResponse;
    }

    public static CapitalCityResponse testCapitalCityTemplate(String capital) {
        CapitalCityResponse capitalCityResponse = new CapitalCityResponse();
        capitalCityResponse.setCapitalCityResult(capital);
        return capitalCityResponse;
    }

    public static LanguageNameResponse testLanguageNameTemplate(String language) {
        LanguageNameResponse languageNameResponse = new LanguageNameResponse();
        languageNameResponse.setLanguageNameResult(language);
        return languageNameResponse;
    }

    public static TContinent testTContinentTemplate(String ISOCode, String name) {
        TContinent continent = new TContinent();
        continent.setSCode(ISOCode);
        continent.setSName(name);
        return continent;
    }

    public static ContinentDto testContinentDtoTemplate(String code, String name) {
        ContinentDto continentDto = new ContinentDto();
        continentDto.setCode(code);
        continentDto.setName(name);
        return continentDto;
    }

    public static CountryDto testCountryDtoTemplate(String code, String name) {
        CountryDto countryDto = new CountryDto();
        countryDto.setCode(code);
        countryDto.setName(name);
        return countryDto;
    }

    public static TCountryCodeAndName testCountryTemplate(String code, String name) {
        TCountryCodeAndName tCountry = new TCountryCodeAndName();
        tCountry.setSISOCode(code);
        tCountry.setSName(name);
        return tCountry;
    }

    public static CurrencyDto testCurrencyDtoTemplate(String code, String name) {
        CurrencyDto currencyDto = new CurrencyDto();
        currencyDto.setCode(code);
        currencyDto.setName(name);
        return currencyDto;
    }

    public static TCurrency testCurrencyTemplate(String code, String name) {
        TCurrency tCurrency = new TCurrency();
        tCurrency.setSISOCode(code);
        tCurrency.setSName(name);
        return tCurrency;
    }

    public static CountryNameResponse createCountryNameItaly() {
        return testCountryNameTemplate("Italy");
    }

    public static CountryNameResponse createCountryNameException() {
        return testCountryNameTemplate("No country found by that name");
    }

    public static CapitalCityResponse createCapitalCityBucharest() {
        return testCapitalCityTemplate("Bucharest");
    }

    public static LanguageNameResponse createLanguageNameSpanish() {
        return testLanguageNameTemplate("spanish");
    }

    public static ContinentDto createContinentDto() {
        return testContinentDtoTemplate("EU", "Europe");
    }

    public static ListOfContinentsByNameResponse createListOfContinents() {
        ListOfContinentsByNameResponse list= new ListOfContinentsByNameResponse();
        ArrayOftContinent arrayOftContinent = new ArrayOftContinent();
        arrayOftContinent.getTContinent().add(testTContinentTemplate("EU", "Europe"));
        arrayOftContinent.getTContinent().add(testTContinentTemplate("AS", "Asia"));
        arrayOftContinent.getTContinent().add(testTContinentTemplate("AF", "Africa"));
        list.setListOfContinentsByNameResult(arrayOftContinent);
        return list;
    }

    public static CountryDto createCountryDto() {
        return testCountryDtoTemplate("ROU", "Romania");
    }

    public static ListOfCountryNamesByNameResponse createListOfCountries() {
        ListOfCountryNamesByNameResponse list = new ListOfCountryNamesByNameResponse();
        ArrayOftCountryCodeAndName arrayOftCountry = new ArrayOftCountryCodeAndName();
        arrayOftCountry.getTCountryCodeAndName().add(testCountryTemplate("ROU","Romania"));
        arrayOftCountry.getTCountryCodeAndName().add(testCountryTemplate("IT","Italy"));
        arrayOftCountry.getTCountryCodeAndName().add(testCountryTemplate("ES","Spain"));
        list.setListOfCountryNamesByNameResult(arrayOftCountry);
        return list;
    }

    public static CurrencyDto createCurrencyDto() {
        return testCurrencyDtoTemplate("EUR", "Euro");
    }

    public static ListOfCurrenciesByCodeResponse createListOfCurrency() {
        ListOfCurrenciesByCodeResponse list = new ListOfCurrenciesByCodeResponse();
        ArrayOftCurrency arrayOftCurrency = new ArrayOftCurrency();
        arrayOftCurrency.getTCurrency().add(testCurrencyTemplate("EUR", "Euro"));
        arrayOftCurrency.getTCurrency().add(testCurrencyTemplate("RON", "Romanian Leu"));
        arrayOftCurrency.getTCurrency().add(testCurrencyTemplate("USD", "US Dollar"));
        list.setListOfCurrenciesByCodeResult(arrayOftCurrency);
        return list;
    }
}
