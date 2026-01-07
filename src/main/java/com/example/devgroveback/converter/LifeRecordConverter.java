package com.example.devgroveback.converter;

import com.example.devgroveback.dao.LifeRecord;
import com.example.devgroveback.dto.LifeRecordDTO;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LifeRecordConverter {

    public static LifeRecordDTO convertToDTO(LifeRecord lifeRecord){
        if (lifeRecord == null) {
            return null;
        }

        LifeRecordDTO dto = new LifeRecordDTO();
        dto.setId(lifeRecord.getId());
        dto.setTitle(lifeRecord.getTitle());
        dto.setMood(lifeRecord.getMood());
        dto.setContent(lifeRecord.getContent());
        dto.setCover(lifeRecord.getCover());
        dto.setLifeDate(lifeRecord.getLifeDate());
        dto.setCreatedAt(lifeRecord.getCreatedAt());
        return dto;
    }

    public static List<LifeRecordDTO> convertToDTO(List<LifeRecord> lifeRecords) {
        if (lifeRecords == null || lifeRecords.isEmpty()) {
            return Collections.emptyList();
        }

        return lifeRecords.stream()
                .map(LifeRecordConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public static LifeRecord covertToEntity(LifeRecordDTO dto){
        if (dto == null) {
            return null;
        }
        LifeRecord lifeRecord = new LifeRecord();
        lifeRecord.setId(dto.getId());
        lifeRecord.setTitle(dto.getTitle());
        lifeRecord.setMood(dto.getMood());
        lifeRecord.setContent(dto.getContent());
        lifeRecord.setCover(dto.getCover());
        lifeRecord.setLifeDate(dto.getLifeDate());
        lifeRecord.setCreatedAt(dto.getCreatedAt());

        return lifeRecord;
    }

}
