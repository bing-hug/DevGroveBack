package com.example.devgroveback.controller;

import com.example.devgroveback.Response;
import com.example.devgroveback.dto.LifeRecordDTO;
import com.example.devgroveback.service.LifeRecordService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class LifeRecordController {
    @Autowired
    private LifeRecordService lifeRecordService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/lifeRecords")
    public Response<List<LifeRecordDTO>> getAllLifeRecords() {
        List<LifeRecordDTO> records = lifeRecordService.getAllRecords();
        return Response.newSuccess(records);
    }

    @GetMapping("/lifeRecords/byLifeState")
    public Response<List<LifeRecordDTO>> getLifeRecordsByLifeState(@RequestParam("life_date") LocalDate LifeDate) {
        List<LifeRecordDTO> records = lifeRecordService.getLifeRecordsByLifeDate(LifeDate);
        return Response.newSuccess(records);
    }

    @PostMapping("/createLifeRecord")
    public Response<String> createLifeRecord(HttpServletRequest request) {
        try {
            String rawJson = request.getReader()
                    .lines()
                    .collect(Collectors.joining());

            LifeRecordDTO record = objectMapper.readValue(rawJson, LifeRecordDTO.class);

            lifeRecordService.createLifeRecord(record);
            return  Response.newSuccess("success");
        } catch (Exception e) {
            log.error("处理请求失败: {}", e.getMessage());
            return Response.newFail(e.getMessage());
        }
    }

    @PostMapping("/delLifeRecord")
    public Response<String> delLifeRecord(@RequestParam long id) {
        lifeRecordService.deleteLifeRecord(id);
        return Response.newSuccess("success");
    }
}
