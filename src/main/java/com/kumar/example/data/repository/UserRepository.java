package com.kumar.example.data.repository;

import com.kumar.example.data.entity.User;
import com.kumar.example.service.dto.UserDTO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    UserDTO findByUserName(String userName);
}
