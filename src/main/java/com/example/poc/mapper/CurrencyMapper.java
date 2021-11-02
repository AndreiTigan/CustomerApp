package com.example.poc.mapper;

import com.example.poc.model.entity.Currency;
import com.example.poc.wsdl_classes.ListOfCurrenciesByCodeResponse;
import com.example.poc.wsdl_classes.TCurrency;

import java.util.List;
import java.util.stream.Collectors;

public class CurrencyMapper {
    public static Currency mapToCurrency(TCurrency tCurrency) {
        Currency currency = new Currency();
        currency.setName(tCurrency.getSName());
        currency.setCode(tCurrency.getSISOCode());
        return currency;
    }

    public static TCurrency mapToTCurrency(Currency currency) {
        TCurrency tCurrency = new TCurrency();
        tCurrency.setSName(currency.getName());
        tCurrency.setSISOCode(currency.getCode());
        return tCurrency;
    }

    public static List<Currency> mapToCurrencyList(ListOfCurrenciesByCodeResponse list) {
        return list.getListOfCurrenciesByCodeResult().getTCurrency()
                .stream()
                .map(CurrencyMapper::mapToCurrency)
                .collect(Collectors.toList());
    }
}
