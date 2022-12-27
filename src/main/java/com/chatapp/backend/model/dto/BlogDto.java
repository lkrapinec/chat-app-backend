package com.chatapp.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogDto {
    private Long id;

    private String title;

    private String description;

    private String text;

    private LocalDateTime dateOfCreation;


    private LocalDateTime dateOfUpdate;

    private String author;

    private List<CommentDto> comments;
}
