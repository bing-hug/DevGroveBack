package com.example.devgroveback.converter;

import java.util.stream.Collectors;

import com.example.devgroveback.dao.Plan;
import com.example.devgroveback.dto.PlanDTO;

import java.util.Collections;
import java.util.List;

public class PlanConverter {

    public static PlanDTO convertToDTO(Plan plan) {
        if (plan == null) {
            return null;
        }

        PlanDTO dto = new PlanDTO();
        dto.setId(plan.getId());
        dto.setTitle(plan.getTitle());
        dto.setDescription(plan.getDescription());
        dto.setPriority(plan.getPriority());

        return dto;
    }

    // 新增：列表转换方法
    public static List<PlanDTO> convertToDTO(List<Plan> plans) {
        if (plans == null || plans.isEmpty()) {
            return Collections.emptyList();
        }
        return plans.stream()
                .map(PlanConverter::convertToDTO) // 复用单个转换方法
                .collect(Collectors.toList());
    }

    public static Plan convertToEntity(PlanDTO dto) {
        if (dto == null) {
            return null;
        }

        Plan plan = new Plan();
        plan.setId(dto.getId());
        plan.setTitle(dto.getTitle());
        plan.setDescription(dto.getDescription());
        plan.setPriority(dto.getPriority());

        return plan;
    }
}
