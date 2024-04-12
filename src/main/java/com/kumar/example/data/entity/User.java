package com.kumar.example.data.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
@NoArgsConstructor
public class User {

    private ObjectId id;
    @Indexed(unique = true)
    @NonNull
    private String userName;
    @NonNull
    private String password;
    //store referenceID of journal
    // DFRef("journal", ObjectId("122222"))
    // foreign key from other table of primarykey
    @DBRef
    private List<Journal> journalList = new ArrayList<>();
}
