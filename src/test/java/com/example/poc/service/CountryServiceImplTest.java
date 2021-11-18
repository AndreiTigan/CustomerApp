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
import com.example.poc.wsdl_classes.ListOfContinentsByName;
import com.example.poc.wsdl_classes.ListOfCountryNamesByName;
import com.example.poc.wsdl_classes.ListOfCurrenciesByCode;
import com.example.poc.wsdl_classes.TContinent;
import com.example.poc.wsdl_classes.TCountryCodeAndName;
import com.example.poc.wsdl_classes.TCurrency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.example.poc.utils.TestUtils.createCapitalCityBucharest;
import static com.example.poc.utils.TestUtils.createContinentDto;
import static com.example.poc.utils.TestUtils.createCountryDto;
import static com.example.poc.utils.TestUtils.createCountryNameException;
import static com.example.poc.utils.TestUtils.createCountryNameItaly;
import static com.example.poc.utils.TestUtils.createCurrencyDto;
import static com.example.poc.utils.TestUtils.createLanguageNameSpanish;
import static com.example.poc.utils.TestUtils.createListOfContinents;
import static com.example.poc.utils.TestUtils.createListOfCountries;
import static com.example.poc.utils.TestUtils.createListOfCurrency;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
class CountryServiceImplTest {

    @Mock
    private SoapRequest soapRequest;

    @Mock
    private CurrencyMapper currencyMapper;

    @Mock
    private CountryMapper countryMapper;

    @Mock
    private ContinentMapper continentMapper;

    @InjectMocks
    private CountryServiceImpl countryServiceImpl;

    @BeforeEach()
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getCountryNameDtoIfCountryISOCodeIsIT() {
        when(soapRequest.countryNameSoapResponse(any(CountryName.class))).thenReturn(createCountryNameItaly());
        CountryNameDto response = countryServiceImpl.getCountryName("IT");
        assertEquals(createCountryNameItaly().getCountryNameResult(), response.getName());
    }

    @Test
    public void getCountryNameDtoIfCountryISOCodeNotExistsAndThrowError() {
        String nonExistingISOCode = "ZY";
        when(soapRequest.countryNameSoapResponse(any(CountryName.class))).thenReturn(createCountryNameException());
        assertEquals(
        "No country found by that name",
        countryServiceImpl.getCountryName(nonExistingISOCode).getName());
    }

    @Test
    public void getCapitalDtoIfCountryISOCodeIsROU() {
        when(soapRequest.capitalCitySoapResponse(any(CapitalCity.class))).thenReturn(createCapitalCityBucharest());
        CapitalDto response = countryServiceImpl.getCapitalCity("ROU");
        assertEquals(createCapitalCityBucharest().getCapitalCityResult(), response.getName());
        verify(soapRequest, times(1)).capitalCitySoapResponse(any(CapitalCity.class));
    }

    @Test
    public void getLanguageNameDtoIfCountryISOCodeISES() {
        when(soapRequest.languageNameSoapResponse(any(LanguageName.class))).thenReturn(createLanguageNameSpanish());
        LanguageDto response = countryServiceImpl.getLanguageName("ES");
        assertEquals(createLanguageNameSpanish().getLanguageNameResult(), response.getName());
    }

    @Test
    public void getContinentsIfListIsNotEmpty() {
        when(continentMapper.convertToDto(any(TContinent.class))).thenReturn(createContinentDto());
        when(soapRequest.listOfContinentsSoapResponse(any(ListOfContinentsByName.class)))
            .thenReturn(createListOfContinents());
        List<ContinentDto> continentDtos = countryServiceImpl.getContinents();
        assertNotNull(continentDtos);
        assertEquals(3, continentDtos.size());
        assertTrue(continentDtos.stream().anyMatch(continentDto -> continentDto.getName().equals(createContinentDto().getName())));
        verify(soapRequest, times(1)).listOfContinentsSoapResponse(any(ListOfContinentsByName.class));
    }

    @Test
    public void getCountriesIfListIsNotEmpty() {
        when(countryMapper.convertToDto(any(TCountryCodeAndName.class))).thenReturn(createCountryDto());
        when(soapRequest.listOfCountriesSoapResponse(any(ListOfCountryNamesByName.class)))
                .thenReturn(createListOfCountries());
        List<CountryDto> countryDtos = countryServiceImpl.getCountries();
        assertNotNull(countryDtos);
        assertEquals(3, countryDtos.size());
        assertTrue(countryDtos.stream().anyMatch(countryDto -> countryDto.getCode().equals(createCountryDto().getCode())));
        verify(soapRequest, times(1)).listOfCountriesSoapResponse(any(ListOfCountryNamesByName.class));
    }

    @Test
    public void getCurrenciesIfListIsNotEmpty() {
        when(currencyMapper.convertToDto(any(TCurrency.class))).thenReturn(createCurrencyDto());
        when(soapRequest.listOfCurrenciesSoapResponse(any(ListOfCurrenciesByCode.class)))
                .thenReturn(createListOfCurrency());
        List<CurrencyDto> currencyDtos = countryServiceImpl.getCurrencyByCode();
        assertNotNull(currencyDtos);
        assertEquals(3, currencyDtos.size());
        assertTrue(currencyDtos.stream().anyMatch(currencyDto -> currencyDto.getCode().equals(createCurrencyDto().getCode())));
        verify(soapRequest, times(1)).listOfCurrenciesSoapResponse(any(ListOfCurrenciesByCode.class));
    }
}