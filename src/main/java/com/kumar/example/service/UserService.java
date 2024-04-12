package com.kumar.example.service;

import com.kumar.example.data.entity.User;
import com.kumar.example.data.repository.UserRepository;
import com.kumar.example.service.dto.UserDTO;
import com.kumar.example.service.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public final UserMapper userMapper;

    public UserDTO saveEntry(UserDTO userDTO) {
        log.debug("before save dto ->{}", userDTO);
        final User save = userRepository.save(userMapper.toEntity(userDTO));
        log.debug("after save entity ->{}", save);
        return userMapper.toDto(save);
    }
    public List<UserDTO> getAll() {
        final List<User> users = userRepository.findAll();
        return userMapper.toDto(users);
    }

    public Optional<UserDTO> getUserById(ObjectId id) {
        final Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            final UserDTO dto = userMapper.toDto(user.get());
            return Optional.ofNullable(dto);
        }
        return Optional.empty();
    }

    public void deleteByById(ObjectId id) {
        userRepository.deleteById(id);
    }

    public UserDTO findByUserName(String username){
        return userRepository.findByUserName(username);
    }
}
