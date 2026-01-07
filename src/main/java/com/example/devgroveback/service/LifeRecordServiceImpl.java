package com.example.devgroveback.service;

import com.example.devgroveback.converter.LifeRecordConverter;
import com.example.devgroveback.converter.PlanConverter;
import com.example.devgroveback.dao.LifeRecord;
import com.example.devgroveback.dao.LifeRecordRepository;
import com.example.devgroveback.dto.LifeRecordDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Service
public class LifeRecordServiceImpl implements LifeRecordService {
    @Autowired
    private LifeRecordRepository lifeRecordRepository;

    @Override
    public List<LifeRecordDTO> getAllRecords() {
        List<LifeRecord> records = lifeRecordRepository.findAll();
        return LifeRecordConverter.convertToDTO(records);
    }

    @Override
    public void createLifeRecord(LifeRecordDTO record) {
        LifeRecord lifeRecord = LifeRecordConverter.covertToEntity(record);
        lifeRecordRepository.save(lifeRecord);
    }

    @Override
    @Transactional
    public void deleteLifeRecord(Long id) {
        lifeRecordRepository.deleteById(id);
    }

    @Override
    public List<LifeRecordDTO> getLifeRecordsByLifeDate(LocalDate lifeDate) {
        List<LifeRecord> records = lifeRecordRepository.findByLifeDateBetween(lifeDate, lifeDate.plusDays(1));
        return LifeRecordConverter.convertToDTO(records);
    }

}
