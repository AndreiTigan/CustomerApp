package com.example.poc.mapper;

import java.util.List;

public interface Mapper<T, U, V> {
    U convertToDto(T t);
    T convertToEntity(U u);
    List<U> convertToDtoList(V v);
}
