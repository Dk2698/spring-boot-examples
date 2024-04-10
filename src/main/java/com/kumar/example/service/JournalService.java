package com.kumar.example.service;

import com.kumar.example.data.entity.Journal;
import com.kumar.example.data.repository.JournalRepository;
import com.kumar.example.service.dto.JournalDTO;
import com.kumar.example.service.mapper.JournalMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class JournalService {
    private final JournalRepository journalRepository;
    public final JournalMapper journalMapper;

    public JournalDTO saveEntry(JournalDTO journalDTO){
        log.debug("before save dto ->{}", journalDTO);
        journalDTO.setDate(new Date());
        final Journal save = journalRepository.save(journalMapper.toEntity(journalDTO));
        log.debug("after save entity ->{}", save);
        return journalMapper.toDto(save);
    }

    public List<JournalDTO> getAll(){
        final List<Journal> journals = journalRepository.findAll();
        return journalMapper.toDto(journals);
    }

    public Optional<JournalDTO> getJournalById(String id) {
        final Optional<Journal> journal = journalRepository.findById(id);
        if(journal.isPresent()){
            final JournalDTO dto = journalMapper.toDto(journal.get());
            return Optional.ofNullable(dto);
        }
        return Optional.empty();
    }

    public void deleteByById(String id) {
        journalRepository.deleteById(id);
    }
}