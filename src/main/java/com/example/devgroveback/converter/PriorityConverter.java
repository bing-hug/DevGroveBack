package com.example.devgroveback.converter;

import com.example.devgroveback.enums.Priority;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;;

@Converter
public class PriorityConverter implements AttributeConverter<Priority, String> {
    @Override
    public String convertToDatabaseColumn(Priority priority) {
        return priority != null ? priority.getCode() : null;
    }

    @Override
    public Priority convertToEntityAttribute(String code) {
        return Priority.getPriorityByValue(code).orElse(null);
    }

}
