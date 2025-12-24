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

import java.time.LocalDateTime;

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

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Convert(converter = PriorityConverter.class)
    private Priority priority;

    private String remark;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "is_completed")
    private Boolean isCompleted;

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }
}
