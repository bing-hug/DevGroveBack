package com.example.devgroveback.service;

import com.example.devgroveback.dto.PlanDTO;

import java.util.List;

public interface PlanService {
    PlanDTO getPlanByID(long id);

    List<PlanDTO> getAllPlans();

    List<PlanDTO> getPlansByStartDate(java.time.LocalDateTime startDate);

    default void createPlan(PlanDTO planInfo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createPlan'");
    }

    Boolean changeCompleteStatus(Long id, Boolean isCompleted);

    Boolean changeRemark(Long id, String remark);

    void deletePlan(Long id);

}
