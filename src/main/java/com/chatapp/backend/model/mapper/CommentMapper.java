package com.chatapp.backend.model.mapper;

import com.chatapp.backend.model.Comment;
import com.chatapp.backend.model.dto.CommentDTO;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
  public CommentDTO toDto(Comment comment) {
    return new CommentDTO(
        comment.getId(),
        comment.getText(),
        comment.getDateOfCreation(),
        comment.getDateOfUpdate(),
        comment.getAuthor()
    );
  }

  public Comment fromDto(CommentDTO commentDTO) {
    return new Comment(
        commentDTO.getId(),
        commentDTO.getText(),
        commentDTO.getDateOfCreation(),
        commentDTO.getDateOfUpdate(),
        commentDTO.getAuthor()
    );
  }

  public Comment updateComment(CommentDTO commentDTO, Comment comment) {
    if (commentDTO.getText() != null) {
      comment.setText(commentDTO.getText());
    }
    if (commentDTO.getDateOfCreation() != null) {
      comment.setDateOfCreation(commentDTO.getDateOfCreation());
    }
    if (commentDTO.getDateOfUpdate() != null) {
      comment.setDateOfUpdate(commentDTO.getDateOfUpdate());
    }
    if (commentDTO.getAuthor() != null) {
      comment.setAuthor(commentDTO.getAuthor());
    }

    return comment;
  }
}
