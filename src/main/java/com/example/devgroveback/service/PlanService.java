package com.example.devgroveback.service;

import com.example.devgroveback.dto.PlanDTO;

import java.util.List;

public interface PlanService {
    PlanDTO getPlanByID(long id);

    List<PlanDTO> getAllPlans();
}
