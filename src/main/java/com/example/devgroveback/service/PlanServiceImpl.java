package com.example.devgroveback.service;

import com.example.devgroveback.converter.PlanConverter;
import com.example.devgroveback.dao.Plan;
import com.example.devgroveback.dao.PlanRepository;
import com.example.devgroveback.dto.PlanDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StreamUtils;

import java.util.List;
import java.util.Optional;

@Service
public class PlanServiceImpl implements PlanService {
    @Autowired
    private PlanRepository planRepository;

    @Override
    public PlanDTO getPlanByID(long id) {
        Plan plan = planRepository.findById(id).orElse(null);
        return PlanConverter.convertToDTO(plan);
    }

    @Override
    public List<PlanDTO> getAllPlans() {
        List<Plan> plans = planRepository.findAll();
        return PlanConverter.convertToDTO(plans);
    }

}
