package com.example.poc.mapper;

import java.util.List;

public interface Mapper<T, U> {
    U convertToDto(T t);
    T convertToEntity(U u);
}
