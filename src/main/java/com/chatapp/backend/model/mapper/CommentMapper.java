package com.chatapp.backend.model.mapper;

import com.chatapp.backend.model.Comment;
import com.chatapp.backend.model.dto.CommentDto;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public CommentDto toDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getText(),
                comment.getDateOfCreation(),
                comment.getDateOfUpdate(),
                comment.getAuthor()
        );
    }

    public Comment fromDto(CommentDto commentDTO) {
        return new Comment(
                commentDTO.getId(),
                commentDTO.getText(),
                commentDTO.getDateOfCreation(),
                commentDTO.getDateOfUpdate(),
                commentDTO.getAuthor()
        );
    }

    public Comment updateComment(CommentDto commentDTO, Comment comment) {
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
