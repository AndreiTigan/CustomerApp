package com.example.poc.controller;

import com.example.poc.service.CountryService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/countries")
@OpenAPIDefinition
@Tag(name = "Countries")
public class CountryController {
    private final CountryService countryService;
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping(value = "/{ISOCode}", produces = "application/json")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved a country by ISOCode")
    public ResponseEntity getCountryName(@PathVariable String ISOCode) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(countryService.getCountryName(ISOCode));
        }
        catch (RuntimeException e) {
            LOGGER.info("Failed to make request to the sever. Server has some issues!");
            LOGGER.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("You cannot access the request, the provider has some problems!");
        }
    }

    @GetMapping(value = "/capital/{ISOCode}", produces = "application/json")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved a capital country by ISOCode")
    public ResponseEntity getCapitalCityByISOCode(@PathVariable String ISOCode) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(countryService.getCapitalCity(ISOCode));
        }
        catch (RuntimeException e) {
            LOGGER.info("Failed to make request to the sever. Server has some issues!");
            LOGGER.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("You cannot access the request, the provider has some problems!");
        }
    }

    @GetMapping(value = "/language/{ISOCode}", produces = "application/json")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved a language name by ISOCode")
    public ResponseEntity getLanguageName(@PathVariable String ISOCode) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(countryService.getLanguageName(ISOCode));
        }
        catch (RuntimeException e) {
            LOGGER.info("Failed to make request to the sever. Server has some issues!");
            LOGGER.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("You cannot access the request, the provider has some problems!");
        }
    }

    @GetMapping(value = "/continents", produces = "application/json")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all continents")
    public ResponseEntity getAllContinents() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(countryService.getContinents());
        }
        catch (RuntimeException e) {
            LOGGER.info("Failed to make request to the sever. Server has some issues!");
            LOGGER.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("You cannot access the request, the provider has some problems!");
        }
    }

    @GetMapping(value = "/allCountries", produces = "application/json")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all countries")
    public ResponseEntity getAllCountries(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(countryService.getCountries());
        }
        catch (RuntimeException e) {
            LOGGER.info("Failed to make request to the sever. Server has some issues!");
            LOGGER.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("You cannot access the request, the provider has some problems!");
        }
    }

    @GetMapping(value="/currencyByCode", produces = "application/json")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all currency by code")
    public ResponseEntity getAllCurrencyByCode() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(countryService.getCurrencyByCode());
        }
        catch (RuntimeException e) {
            LOGGER.info("Failed to make request to the sever. Server has some issues!");
            LOGGER.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("You cannot access the request, the provider has some problems!");
        }
    }
}
