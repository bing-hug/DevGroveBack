package com.example.devgroveback.dao;

import com.example.devgroveback.enums.Priority;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.devgroveback.converter.PriorityConverter;

// 自增
import static jakarta.persistence.GenerationType.IDENTITY;

import java.sql.Date;

@Entity
@Table(name = "plans")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Plan {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Date startDate;

    @Convert(converter = PriorityConverter.class)
    private Priority priority;

    private String remark;
}
