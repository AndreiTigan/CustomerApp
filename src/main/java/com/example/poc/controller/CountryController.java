package com.example.poc.controller;

import com.example.poc.model.entity.Capital;
import com.example.poc.model.entity.Continent;
import com.example.poc.model.entity.Country;
import com.example.poc.model.entity.CountryNameEntity;
import com.example.poc.model.entity.Currency;
import com.example.poc.model.entity.Language;
import com.example.poc.service.CountryService;
import com.example.poc.wsdl_classes.LanguageNameResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/countries")
@OpenAPIDefinition
@Tag(name = "Countries")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping(value = "/{ISOCode}", produces = "application/json")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved a country by ISOCode")
    public ResponseEntity<CountryNameEntity> getCountryName(@PathVariable String ISOCode) {
        return ResponseEntity.status(HttpStatus.OK).body(countryService.getCountryName(ISOCode));
    }

    @GetMapping(value = "/capital/{ISOCode}", produces = "application/json")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved a capital country by ISOCode")
    public ResponseEntity<Capital> getCapitalCityByISOCode(@PathVariable String ISOCode) {
        return ResponseEntity.status(HttpStatus.OK).body(countryService.getCapitalCity(ISOCode));
    }

    @GetMapping(value = "/language/{ISOCode}", produces = "application/json")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved a language name by ISOCode")
    public ResponseEntity<Language> getLanguageName(@PathVariable String ISOCode) {
        return ResponseEntity.status(HttpStatus.OK).body(countryService.getLanguageName(ISOCode));
    }

    @GetMapping(value = "/continents", produces = "application/json")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all continents")
    public ResponseEntity<List<Continent>> getAllContinents() {
        return ResponseEntity.status(HttpStatus.OK).body(countryService.getContinents());
    }

    @GetMapping(value = "/allCountries", produces = "application/json")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all countries")
    public ResponseEntity<List<Country>> getAllCountries(){
        return ResponseEntity.status(HttpStatus.OK).body(countryService.getCountries());
    }

    @GetMapping(value="/currencyByCode", produces = "application/json")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all currency by code")
    public ResponseEntity<List<Currency>> getAllCurrencyByCode() {
        return ResponseEntity.status(HttpStatus.OK).body(countryService.getCurrencyByCode());
    }
}
