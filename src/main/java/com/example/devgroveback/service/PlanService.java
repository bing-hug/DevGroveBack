package com.example.devgroveback.service;

import com.example.devgroveback.dto.PlanDTO;

import java.util.List;

public interface PlanService {
    PlanDTO getPlanByID(long id);

    List<PlanDTO> getAllPlans();

    default void createPlan(PlanDTO planInfo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createPlan'");
    }

}
