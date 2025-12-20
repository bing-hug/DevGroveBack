package com.example.devgroveback.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.sql.Date;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long>, JpaSpecificationExecutor<Plan> {

    List<Plan> findByStartDate(Date startDate);

    @Query("SELECT p FROM Plan p WHERE p.startDate < CURRENT_DATE")
    List<Plan> findExpiredActivePlans();

}
