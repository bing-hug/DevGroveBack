package com.example.devgroveback.controller;

import com.example.devgroveback.Response;
import com.example.devgroveback.dto.PlanDTO;
import com.example.devgroveback.service.PlanService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Slf4j
@RestController
public class PlanController {
    @Autowired
    private PlanService planService;

    @Autowired
    private ObjectMapper objectMapper; // Spring会自动提供

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

    @PostMapping("/createPlan")
    public Response<String> createPlan(HttpServletRequest request) {
        try {
            // 1. 手动读取原始请求体
            String rawJson = request.getReader()
                    .lines()
                    .collect(java.util.stream.Collectors.joining());

            // 2. 打印到控制台
            log.info("=== 前端传过来的原始JSON ===");
            log.info(rawJson);
            log.info("=== 结束 ===");

            // 3. 尝试解析为对象
            PlanDTO planInfo = objectMapper.readValue(rawJson, PlanDTO.class);
            log.info("解析后的对象: {}", planInfo);

            // 4. 业务处理
            planService.createPlan(planInfo);
            return Response.newSuccess("");

        } catch (Exception e) {
            log.error("处理请求失败: {}", e.getMessage());
            e.printStackTrace(); // 打印完整堆栈
            return Response.newSuccess("请求参数错误");
        }
    }

}
