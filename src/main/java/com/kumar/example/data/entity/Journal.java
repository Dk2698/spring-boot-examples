package com.kumar.example.data.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Document
public class Journal {

    @Id
    private String id;

    private String title;

    private String content;

    private Date date;
}
