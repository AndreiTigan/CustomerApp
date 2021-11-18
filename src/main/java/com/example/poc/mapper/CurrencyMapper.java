package com.example.poc.mapper;

import com.example.poc.model.dto.CurrencyDto;
import com.example.poc.wsdl_classes.TCurrency;
import org.springframework.stereotype.Component;

@Component
public class CurrencyMapper implements Mapper<TCurrency, CurrencyDto> {

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
}
