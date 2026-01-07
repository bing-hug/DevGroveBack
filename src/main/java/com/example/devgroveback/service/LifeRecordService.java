package com.example.devgroveback.service;

import com.example.devgroveback.dto.LifeRecordDTO;

import java.util.List;
import java.time.LocalDate;

public interface LifeRecordService {
    List<LifeRecordDTO> getAllRecords();
    List<LifeRecordDTO> getLifeRecordsByLifeDate(LocalDate lifeDate);

    default void createLifeRecord(LifeRecordDTO lifeRecordDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createPlan'");
    }

    void deleteLifeRecord(Long id);
}
