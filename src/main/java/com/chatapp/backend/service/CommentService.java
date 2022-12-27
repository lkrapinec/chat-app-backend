package com.chatapp.backend.service;

import com.chatapp.backend.model.dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto getComment(Long id);

    List<CommentDto> getAllComments(Long blogId);

    CommentDto saveComment(CommentDto commentDTO, Long blogId);

    CommentDto updateComment(CommentDto commentDTO);

    void deleteComment(Long id);
}
