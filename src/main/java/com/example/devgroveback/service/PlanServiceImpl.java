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

    @Override
    public void createPlan(PlanDTO planInfo) {
        Plan plan = PlanConverter.convertToEntity(planInfo);
        planRepository.save(plan);
    }

    @Override
    @Transactional
    public Boolean changeCompleteStatus(Long id, Boolean isCompleted) {
        Optional<Plan> optionalPlan = planRepository.findById(id);
        if (optionalPlan.isPresent()) {
            Plan plan = optionalPlan.get();
            plan.setIsCompleted(isCompleted);
            planRepository.save(plan);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void deletePlan(Long id) {
        planRepository.deleteById(id);
    }

    @Override
    public List<PlanDTO> getPlansByStartDate(java.time.LocalDateTime startDate) {
        List<Plan> plans = planRepository.findByStartDateBetween(startDate, startDate.plusDays(1));
        return PlanConverter.convertToDTO(plans);
    }

    @Override
    @Transactional
    public Boolean changeRemark(Long id, String remark) {
        Optional<Plan> optionalPlan = planRepository.findById(id);
        if (optionalPlan.isPresent()) {
            Plan plan = optionalPlan.get();
            plan.setRemark(remark);
            planRepository.save(plan);
            return true;
        }
        return false;
    }
}
