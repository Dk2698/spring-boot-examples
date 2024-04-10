package com.kumar.example.web.controller;

import com.kumar.example.service.JournalService;
import com.kumar.example.service.dto.JournalDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/journals")
@AllArgsConstructor
@Slf4j
public class JournalController {

    private final JournalService journalService;

    @PostMapping
    public JournalDTO createEntry(@RequestBody JournalDTO journalDTO){
        log.debug("rest request body ->{}", journalDTO);
        return journalService.saveEntry(journalDTO);
    }

    @GetMapping("/{myId}")
    public JournalDTO getJournalById(@PathVariable(name = "myId") String id){
        return journalService.getJournalById(id).orElse(null);
    }

    @GetMapping
    public List<JournalDTO> getAll(){
        return journalService.getAll();
    }

    @DeleteMapping("/{myId}")
    public void deleteJournalById(@PathVariable(name = "myId") String id){
         journalService.deleteByById(id);
    }

    @PutMapping("/{myId}")
    public JournalDTO updateJournalById(@PathVariable(name = "myId") String id, @RequestBody JournalDTO journalDTO){
        final JournalDTO exitIngjournalDTO = journalService.getJournalById(id).orElse(null);
        if (exitIngjournalDTO != null){
            exitIngjournalDTO.setTitle(journalDTO.getTitle() != null && !journalDTO.getTitle().equals("") ? journalDTO.getTitle() : exitIngjournalDTO.getTitle());
            exitIngjournalDTO.setContent(journalDTO.getContent() != null && !journalDTO.getContent().equals("") ? journalDTO.getContent() : exitIngjournalDTO.getContent());
        }
        return journalService.saveEntry(exitIngjournalDTO);
    }
}
