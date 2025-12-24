package com.example.devgroveback.dto;

import com.example.devgroveback.enums.Priority;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanDTO {
    private Long id;
    private String title;
    private String description;
    @JsonProperty("start_date")
    private LocalDateTime startDate;
    private Priority priority;
    private String remark;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("is_completed")
    private Boolean isCompleted;
}
