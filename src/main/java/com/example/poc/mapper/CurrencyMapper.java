package com.example.poc.mapper;

import com.example.poc.model.dto.CurrencyDto;
import com.example.poc.wsdl_classes.ListOfCurrenciesByCodeResponse;
import com.example.poc.wsdl_classes.TCurrency;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CurrencyMapper implements Mapper<TCurrency, CurrencyDto, ListOfCurrenciesByCodeResponse> {

    @Override
    public TCurrency convertToEntity(CurrencyDto currencyDto) {
        TCurrency tCurrency = new TCurrency();
        tCurrency.setSName(currencyDto.getName());
        tCurrency.setSISOCode(currencyDto.getCode());
        return tCurrency;
    }

    @Override
    public CurrencyDto convertToDto(TCurrency tCurrency) {
        CurrencyDto currencyDto = new CurrencyDto();
        currencyDto.setName(tCurrency.getSName());
        currencyDto.setCode(tCurrency.getSISOCode());
        return currencyDto;
    }

    @Override
    public List<CurrencyDto> convertToDtoList(ListOfCurrenciesByCodeResponse list) {
        return list.getListOfCurrenciesByCodeResult().getTCurrency()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
