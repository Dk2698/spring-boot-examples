package com.kumar.example.data.repository;

import com.kumar.example.data.entity.Journal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalRepository extends MongoRepository<Journal, String> {
}
