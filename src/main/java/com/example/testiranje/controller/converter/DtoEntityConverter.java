package com.example.testiranje.controller.converter;

public interface DtoEntityConverter<T,E> {

    T toDto(E e);

    E toEntity(T t);
}
