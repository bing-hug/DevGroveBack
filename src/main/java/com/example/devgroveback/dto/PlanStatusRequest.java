package com.example.devgroveback.dto;

import lombok.Data;

@Data
public class PlanStatusRequest {
    private Long id;
    private Boolean isCompleted;
}