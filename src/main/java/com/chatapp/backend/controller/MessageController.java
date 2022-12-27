package com.chatapp.backend.controller;

import com.chatapp.backend.model.dto.CommentDto;
import com.chatapp.backend.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
@Slf4j
public class MessageController {
    private final CommentService commentService;

    @Autowired
    public MessageController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{blogId}")
    public ResponseEntity<List<CommentDto>> getAllMessages(@PathVariable Long blogId) {
        log.info("Get all messages from chat with id {}", blogId);

        return ResponseEntity.ok(commentService.getAllComments(blogId));
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateMessage(CommentDto commentDTO) {
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

