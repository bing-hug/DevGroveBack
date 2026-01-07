package com.example.devgroveback.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Builder;

import static jakarta.persistence.GenerationType.IDENTITY;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="life_records")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LifeRecord {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String title;
    private String mood;
    private String content;
    private String cover;

    @Column(name = "life_date")
    private LocalDate lifeDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }
}
