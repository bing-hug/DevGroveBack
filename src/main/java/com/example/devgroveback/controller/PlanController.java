package com.example.devgroveback.controller;

import com.example.devgroveback.Response;
import com.example.devgroveback.dto.PlanDTO;
import com.example.devgroveback.dto.PlanStatusRequest;
import com.example.devgroveback.dto.PlanRemarkRequest;
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

    @GetMapping("/plans/byStartDate")
    public Response<List<PlanDTO>> getPlansByStartDate(@RequestParam("start_date") String startDateStr) {
        java.time.LocalDateTime startDate;
        try {
            // 先尝试完整时间
            startDate = java.time.LocalDateTime.parse(startDateStr);
        } catch (java.time.format.DateTimeParseException e1) {
            try {
                // 尝试 ISO 带Z格式
                startDate = java.time.OffsetDateTime.parse(startDateStr).toLocalDateTime();
            } catch (java.time.format.DateTimeParseException e2) {
                try {
                    // 尝试仅日期
                    startDate = java.time.LocalDate.parse(startDateStr).atStartOfDay();
                } catch (java.time.format.DateTimeParseException e3) {
                    log.error("start_date参数无法解析: {}", startDateStr);
                    throw e3;
                }
            }
        }
        List<PlanDTO> plans = planService.getPlansByStartDate(startDate);
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

    @PostMapping("/setPlanStatus")
    public Response<Boolean> setCompleteStatus(@RequestBody PlanStatusRequest request) {
        if (request == null) {
            log.error("setPlanStatus: Controller未接收到前端数据，可能是JSON格式或字段名错误");
            return Response.newSuccess(false);
        }
        log.info("setPlanStatus: 前端发来的数据: id={}, isCompleted={}", request.getId(), request.getIsCompleted());
        Boolean result = planService.changeCompleteStatus(request.getId(), request.getIsCompleted());
        return Response.newSuccess(result);
    }

    @PostMapping("/delPlan")
    public Response<String> delPlan(@RequestParam long id) {
        planService.deletePlan(id);
        return Response.newSuccess("删除成功");
    }

    @PostMapping("/setPlanRemark")
    public Response<String> setRemark(@RequestBody PlanRemarkRequest request) {
        log.info("setPlanRemark: 前端发来的数据: id={}, remark={}", request, request.getId(), request.getRemark());
        Boolean result = planService.changeRemark(request.getId(), request.getRemark());
        if (result) {
            return Response.newSuccess("备注更新成功");
        } else {
            return Response.newSuccess("备注更新失败，计划不存在");
        }
    }
}
