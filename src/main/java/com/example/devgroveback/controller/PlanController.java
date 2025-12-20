package com.example.devgroveback.controller;

import com.example.devgroveback.Response;
import com.example.devgroveback.dto.PlanDTO;
import com.example.devgroveback.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlanController {
    @Autowired
    private PlanService planService;

    @GetMapping("/plans/{id}")
    public Response<PlanDTO> getPlanById(@PathVariable Long id) {
        PlanDTO plan = planService.getPlanByID(id);
        return Response.newSuccess(plan);
    }

    @GetMapping("/plans")
    public Response<List<PlanDTO>> getAllPlans() {
        List<PlanDTO> plans = planService.getAllPlans();
        return Response.newSuccess(plans);
    }
}
