package com.example.demo.util;

import jakarta.persistence.AttributeConverter;

import java.util.Objects;

public class Converter01 implements AttributeConverter<Boolean, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Boolean attribute) {
        // TODO Auto-generated method stub
        return attribute ?1:0;
    }

    @Override
    public Boolean convertToEntityAttribute(Integer integer) {
        return Objects.equals(1,integer)?Boolean.TRUE:Boolean.FALSE;
    }



}
