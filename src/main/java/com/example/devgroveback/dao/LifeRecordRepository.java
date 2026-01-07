package com.example.devgroveback.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Repository
public interface LifeRecordRepository extends JpaRepository<LifeRecord, Long>, JpaSpecificationExecutor<LifeRecord> {
    List<LifeRecord> findByLifeDate(LocalDate lifeDate);

    @Query("SELECT p FROM LifeRecord p WHERE p.lifeDate >= :start AND p.lifeDate < :end")
    List<LifeRecord> findByLifeDateBetween(@Param("start") LocalDate start, @Param("end") LocalDate end);
}
