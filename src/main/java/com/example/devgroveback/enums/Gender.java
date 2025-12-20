package com.example.devgroveback.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum Gender {
    BOY("男", "M"), GIRL("女", "F");

    private String name;
    private String code;

    public static Optional<Gender> getGenderByValue(String value) {
        return Arrays.stream(Gender.values()).filter(g -> g.getCode().equals(value)).findFirst();
    }
}
