package com.example.poc.mapper;

public interface Mapper<T, U> {
    U convertToDto(T t);
    T convertToEntity(U u);
}
