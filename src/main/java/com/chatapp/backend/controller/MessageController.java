package com.chatapp.backend.controller;

import com.chatapp.backend.model.dto.CommentDTO;
import com.chatapp.backend.service.CommentService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
@Slf4j
public class MessageController {
  private final CommentService commentService;

  @Autowired
  public MessageController(CommentService commentService) {
    this.commentService = commentService;
  }

  @GetMapping("/{blogId}")
  public ResponseEntity<List<CommentDTO>> getAllMessages(@PathVariable Long blogId) {
    log.info("Get all messages from chat with id {}", blogId);

    return ResponseEntity.ok(commentService.getAllComments(blogId));
  }

  @PutMapping
  public ResponseEntity<HttpStatus> updateMessage(CommentDTO commentDTO) {
    log.info("Updating message {}", commentDTO);
    commentService.updateComment(commentDTO);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteMessage(@PathVariable Long id) {
    log.info("Deleting message with id {}", id);
    commentService.deleteComment(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}

