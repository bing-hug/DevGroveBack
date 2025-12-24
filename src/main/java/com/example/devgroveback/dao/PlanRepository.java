package com.example.devgroveback.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDateTime;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long>, JpaSpecificationExecutor<Plan> {

    List<Plan> findByStartDate(LocalDateTime startDate);

    @Query("SELECT p FROM Plan p WHERE p.startDate < CURRENT_DATE")
    List<Plan> findExpiredActivePlans();

    @Query("SELECT p FROM Plan p WHERE p.startDate >= :start AND p.startDate < :end")
    List<Plan> findByStartDateBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
