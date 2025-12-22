package com.example.devgroveback.dto;

import com.example.devgroveback.enums.Priority;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanDTO {
    private Long id;
    private String title;
    private String description;
    private Date startDate;
    private Priority priority;
    private String remark;
}
