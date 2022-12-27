package com.chatapp.backend.service.impl;

import com.chatapp.backend.model.Comment;
import com.chatapp.backend.model.dto.CommentDto;
import com.chatapp.backend.model.mapper.CommentMapper;
import com.chatapp.backend.repository.CommentRepository;
import com.chatapp.backend.service.CommentService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public CommentDto getComment(Long id) {
        return commentRepository.findById(id).map(commentMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Comment with id " + id + " not found"));
    }

    @Override
    public List<CommentDto> getAllComments(Long blogId) {
        return commentRepository.findCommentByBlogId(blogId).stream().map(commentMapper::toDto).toList();
    }

    @Transactional
    @Override
    public CommentDto saveComment(CommentDto commentDTO, Long chatId) {
        if (commentDTO.getId() != null) {
            throw new IllegalArgumentException("Comment id must be empty");
        }
        commentDTO.setDateOfCreation(LocalDateTime.now());
        commentDTO.setDateOfUpdate(LocalDateTime.now());

        Comment comment = commentMapper.fromDto(commentDTO);
        comment = commentRepository.save(comment);

        return commentMapper.toDto(comment);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDTO) {
        Comment comment = commentRepository.findById(commentDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Comment does not exist"));

        comment = commentMapper.updateComment(commentDTO, comment);
        comment = commentRepository.save(comment);

        return commentMapper.toDto(comment);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
