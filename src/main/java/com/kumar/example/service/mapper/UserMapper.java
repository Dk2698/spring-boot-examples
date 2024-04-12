package com.kumar.example.service.mapper;

import com.kumar.example.data.entity.User;
import com.kumar.example.service.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDTO, User>{

}