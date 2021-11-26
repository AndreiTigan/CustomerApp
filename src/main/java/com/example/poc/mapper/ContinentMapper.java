package com.example.poc.mapper;

import com.example.poc.model.dto.ContinentDto;
import com.example.poc.wsdl_classes.TContinent;
import org.springframework.stereotype.Component;

@Component
public class ContinentMapper implements Mapper<TContinent, ContinentDto> {

    @Override
    public ContinentDto convertToDto(TContinent tContinent) {
        ContinentDto continentDto = new ContinentDto();
        continentDto.setName(tContinent.getSName());
        continentDto.setCode(tContinent.getSCode());
        return continentDto;
    }

    @Override
    public TContinent convertToEntity(ContinentDto continentDto) {
        TContinent tContinent = new TContinent();
        tContinent.setSName(continentDto.getName());
        tContinent.setSCode(continentDto.getCode());
        return tContinent;
    }
}
