package com.chatapp.backend.service;

import com.chatapp.backend.model.dto.CommentDTO;
import java.util.List;

public interface CommentService {
  CommentDTO getComment(Long id);

  List<CommentDTO> getAllComments(Long blogId);

  CommentDTO saveComment(CommentDTO commentDTO, Long blogId);

  CommentDTO updateComment(CommentDTO commentDTO);

  void deleteComment(Long id);
}
