package com.kumar.example.web.controller;

import com.kumar.example.service.UserService;
import com.kumar.example.service.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        log.debug("rest request body ->{}", userDTO);
        try {
            final UserDTO userDTO1 = userService.saveEntry(userDTO);
            return new ResponseEntity<>(userDTO1, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{myId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(name = "myId") ObjectId id) {
        final Optional<UserDTO> UserDTO = userService.getUserById(id);
        return UserDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        final List<UserDTO> all = userService.getAll();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{myId}")
    public ResponseEntity<?> deleteJournalById(@PathVariable(name = "myId") ObjectId id) {
        userService.deleteByById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO, @PathVariable(name = "userName") String userName) {
        final UserDTO userInDb = userService.findByUserName(userName);
        if (userInDb != null){
            userInDb.setUserName(userInDb.getUserName());
            userInDb.setPassword(userDTO.getPassword());
            userService.saveEntry(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
