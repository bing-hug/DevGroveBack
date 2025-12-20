
package com.example.devgroveback.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum Priority {
    HIGH("高", "H"),
    MEDIUM("中", "M"),
    LOW("低", "L");

    private String name;
    private String code;

    public static Optional<Priority> getPriorityByValue(String value) {
        return Arrays.stream(Priority.values())
                .filter(p -> p.getCode().equals(value))
                .findFirst();
    }
}
