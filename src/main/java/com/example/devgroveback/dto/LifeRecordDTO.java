package com.example.devgroveback.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LifeRecordDTO {
    private Long id;
    private String title;
    private String cover;
    private String content;
    private String mood;
    @JsonProperty("life_date")
    private LocalDate lifeDate;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
}
