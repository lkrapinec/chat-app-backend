package com.chatapp.backend.model.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
  private Long id;

  private String text;

  private LocalDateTime dateOfCreation;

  private LocalDateTime dateOfUpdate;

  private String author;
}
