package com.example.poc.mapper;

import com.example.poc.model.entity.Continent;
import com.example.poc.wsdl_classes.ListOfContinentsByNameResponse;
import com.example.poc.wsdl_classes.TContinent;

import java.util.List;
import java.util.stream.Collectors;

public class ContinentMapper {
    public static Continent mapToContinent(TContinent tContinent) {
        Continent continent = new Continent();
        continent.setName(tContinent.getSName());
        continent.setCode(tContinent.getSCode());
        return continent;
    }

    public static TContinent mapTOTContinent(Continent continent) {
        TContinent tContinent = new TContinent();
        tContinent.setSName(continent.getName());
        tContinent.setSCode(continent.getCode());
        return tContinent;
    }

    public static List<Continent> mapToContinentList(ListOfContinentsByNameResponse list) {
        return list.getListOfContinentsByNameResult().getTContinent()
                .stream()
                .map(ContinentMapper::mapToContinent)
                .collect(Collectors.toList());
    }
}
