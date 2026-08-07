package com.abhinav.hibernate.model;

import jakarta.persistence.AttributeConverter;


public class BooleanToStringConverter implements AttributeConverter<Boolean , String> {
    @Override
    public String convertToDatabaseColumn(Boolean aBoolean) {
        if(aBoolean != null && aBoolean == true)
            return "Yes";
        return "No";
    }

    @Override
    public Boolean convertToEntityAttribute(String s) {
        if(s.equalsIgnoreCase("Yes"))
            return true;
        return false;
    }
}
