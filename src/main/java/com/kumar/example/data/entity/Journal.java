package com.kumar.example.data.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Document
public class Journal {

    @Id
    private String id;

    @NonNull
    private String title;

    private String content;

    private LocalDateTime date;
}
