package com.kumar.example.service.mapper;

import com.kumar.example.data.entity.Journal;
import com.kumar.example.service.dto.JournalDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JournalMapper extends EntityMapper<JournalDTO, Journal>{

//    @Mapping(target = "id", ignore = true)
//    JournalDTO toWithoutId(Journal journal);
}