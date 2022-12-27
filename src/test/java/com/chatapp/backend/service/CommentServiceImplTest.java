package com.chatapp.backend.service;

import com.chatapp.backend.model.Comment;
import com.chatapp.backend.model.dto.CommentDto;
import com.chatapp.backend.model.mapper.CommentMapper;
import com.chatapp.backend.repository.CommentRepository;
import com.chatapp.backend.service.impl.CommentServiceImpl;
import com.chatapp.backend.util.CommentGenerationgUtil;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {
    @Mock
    private CommentRepository commentRepository;

    @Mock
    private CommentMapper commentMapper;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    @DisplayName("Test get comment with valid id")
    void testGetCommentWithValidId() {
        //given
        Comment comment = CommentGenerationgUtil.generateComment();
        CommentDto commentDTO = CommentGenerationgUtil.generateCommentDTO();
        Long id = comment.getId();

        when(commentMapper.toDto(comment)).thenReturn(commentDTO);
        when(commentRepository.findById(id)).thenReturn(Optional.of(comment));

        //when
        commentService.getComment(id);

        //then
        verify(commentMapper, times(1)).toDto(comment);
        verify(commentRepository, times(1)).findById(id);
        verifyNoMoreInteractions(commentMapper, commentRepository);
    }

    @Test
    @DisplayName("Test get comment with invalid id")
    void testGetCommentWithInvalidId() {
        //given
        Long id = 1L;

        doThrow(EntityNotFoundException.class).when(commentRepository).findById(id);

        //when
        Assertions.assertThrows(EntityNotFoundException.class, () -> commentService.getComment(id));

        //then
        verify(commentRepository, times(1)).findById(id);
        verifyNoMoreInteractions(commentMapper, commentRepository);
    }

    @Test
    @DisplayName("Test get all comments")
    void testGetAllComments() {
        //given
        Comment comment = CommentGenerationgUtil.generateComment();
        CommentDto commentDTO = CommentGenerationgUtil.generateCommentDTO();

        when(commentMapper.toDto(comment)).thenReturn(commentDTO);
        when(commentRepository.findCommentByBlogId(1L)).thenReturn(List.of(comment));

        //when
        commentService.getAllComments(1L);

        //then
        verify(commentMapper, times(1)).toDto(comment);
        verify(commentRepository, times(1)).findCommentByBlogId(1L);
        verifyNoMoreInteractions(commentMapper, commentRepository);
    }

    @Test
    @DisplayName("Test save comment with valid comment")
    void testSaveCommentWithValidComment() {
        //given
        List<Comment> comments = CommentGenerationgUtil.generateCommentList();

        when(commentRepository.findCommentByBlogId(1L)).thenReturn(comments);

        //when
        commentService.getAllComments(1L);

        //then
        verify(commentRepository, times(1)).findCommentByBlogId(1L);
        verify(commentMapper, times(10)).toDto(any());
        verifyNoMoreInteractions(commentMapper, commentRepository);
    }

    @Test
    @DisplayName("Test save comment with invalid comment")
    void testSaveCommentWithInvalidComment() {
        //given
        CommentDto commentDTO = CommentGenerationgUtil.generateCommentDTO();

        //when
        Assertions.assertThrows(IllegalArgumentException.class, () -> commentService.saveComment(commentDTO, 1L));

        //then
        verifyNoInteractions(commentMapper, commentRepository);
    }

    @Test
    @DisplayName("Test update comment with valid comment")
    void testUpdateCommentWithValidComment() {
        //given
        Comment comment = CommentGenerationgUtil.generateComment();
        CommentDto commentDTO = CommentGenerationgUtil.generateCommentDTO();
        Long id = comment.getId();

        when(commentRepository.findById(id)).thenReturn(Optional.of(comment));
        when(commentMapper.updateComment(commentDTO, comment)).thenReturn(comment);
        when(commentRepository.save(comment)).thenReturn(comment);

        //when
        commentService.updateComment(commentDTO);

        //then
        verify(commentRepository, times(1)).findById(id);
        verify(commentMapper, times(1)).updateComment(commentDTO, comment);
        verify(commentRepository, times(1)).save(comment);
        verify(commentMapper, times(1)).toDto(comment);
        verifyNoMoreInteractions(commentMapper, commentRepository);
    }

    @Test
    @DisplayName("Test update comment with null id")
    void testUpdateCommentWithNullId() {
        //given
        CommentDto commentDTO = CommentGenerationgUtil.generateCommentDTOWithoutId();
        Long id = commentDTO.getId();

        //when
        Assertions.assertThrows(EntityNotFoundException.class, () -> commentService.updateComment(commentDTO));

        //then
        verify(commentRepository, times(1)).findById(id);
        verifyNoMoreInteractions(commentMapper, commentRepository);
    }

    @Test
    @DisplayName("Test delete comment with valid id")
    void testDeleteCommentWithValidId() {
        //given
        Long id = 1L;

        doNothing().when(commentRepository).deleteById(id);

        //when
        commentService.deleteComment(id);

        //then
        verify(commentRepository, times(1)).deleteById(id);
        verifyNoMoreInteractions(commentRepository);
    }

    @Test
    @DisplayName("Test delete comment with invalid id")
    void testDeleteCommentWithInvalidId() {
        //given
        Long id = 1L;

        doThrow(IllegalArgumentException.class).when(commentRepository).deleteById(id);

        //when
        Assertions.assertThrows(IllegalArgumentException.class, () -> commentService.deleteComment(id));

        //then
        verify(commentRepository, times(1)).deleteById(id);
        verifyNoMoreInteractions(commentRepository);
    }
}
