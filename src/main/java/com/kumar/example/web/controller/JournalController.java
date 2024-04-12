package com.kumar.example.web.controller;

import com.kumar.example.data.entity.Journal;
import com.kumar.example.service.JournalService;
import com.kumar.example.service.UserService;
import com.kumar.example.service.dto.JournalDTO;
import com.kumar.example.service.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/journals")
@AllArgsConstructor
@Slf4j
public class JournalController {

    private final JournalService journalService;
    private final UserService userService;


    @PostMapping
    public ResponseEntity<JournalDTO> createEntry(@RequestBody JournalDTO journalDTO) {
        log.debug("rest request body ->{}", journalDTO);
        try {
            final JournalDTO journalDTO1 = journalService.saveEntry(journalDTO);
            return new ResponseEntity<>(journalDTO1, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{userName}")
    public ResponseEntity<JournalDTO> createJournalForUser(@RequestBody JournalDTO journalDTO, @PathVariable String userName) {
        log.debug("rest request body ->{}", journalDTO);
        try {
            final JournalDTO journalDTO1 = journalService.createJournalForUser(journalDTO, userName);
            return new ResponseEntity<>(journalDTO1, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{myId}")
    public ResponseEntity<JournalDTO> getJournalById(@PathVariable(name = "myId") String id) {
        final Optional<JournalDTO> journalDTO = journalService.getJournalById(id);
        return journalDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{userName}")
    public ResponseEntity<?> getJournalByUserName(@PathVariable(name = "userName") String userName) {
        final UserDTO existingUserName = userService.findByUserName(userName);
        final List<Journal> journalList = existingUserName.getJournalList();
        if (journalList != null && !journalList.isEmpty()) {
            new ResponseEntity<>(journalList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<JournalDTO>> getAll() {
        final List<JournalDTO> all = journalService.getAll();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{myId}")
    public ResponseEntity<?> deleteJournalById(@PathVariable(name = "myId") String id) {
        journalService.deleteByById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{myId}")
    public ResponseEntity<JournalDTO> updateJournalById(@PathVariable(name = "myId") String id, @RequestBody JournalDTO journalDTO) {
        final JournalDTO exitIngjournalDTO = journalService.getJournalById(id).orElse(null);
        if (exitIngjournalDTO != null) {
            exitIngjournalDTO.setTitle(journalDTO.getTitle() != null && !journalDTO.getTitle().equals("") ? journalDTO.getTitle() : exitIngjournalDTO.getTitle());
            exitIngjournalDTO.setContent(journalDTO.getContent() != null && !journalDTO.getContent().equals("") ? journalDTO.getContent() : exitIngjournalDTO.getContent());
            journalService.saveEntry(exitIngjournalDTO);
            return new ResponseEntity<>(exitIngjournalDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
