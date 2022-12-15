package com.chatapp.backend.model.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogDTO {
  private Long id;

  private String title;

  private String description;

  private String text;

  private LocalDateTime dateOfCreation;


  private LocalDateTime dateOfUpdate;

  private String author;

  private List<CommentDTO> comments;
}
