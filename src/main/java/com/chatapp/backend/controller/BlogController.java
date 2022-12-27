package com.chatapp.backend.controller;

import com.chatapp.backend.model.dto.BlogDto;
import com.chatapp.backend.model.dto.CommentDto;
import com.chatapp.backend.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
@Slf4j
public class BlogController {
    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogDto> getBlog(@PathVariable Long id) {
        log.info("Get blog with id {}", id);

        return ResponseEntity.ok(blogService.getBlog(id));
    }

    @GetMapping
    public ResponseEntity<List<BlogDto>> getAllBlogs() {
        log.info("Get all blogs");

        return ResponseEntity.ok(blogService.getAllBlogs());
    }

    @PostMapping
    public ResponseEntity<BlogDto> createBlog(@RequestBody BlogDto blogDTO) {
        log.info("Saving blog {}", blogDTO);

        return new ResponseEntity<>(blogService.createBlog(blogDTO), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/comment")
    public ResponseEntity<CommentDto> addCommentToBlog(@RequestBody CommentDto commentDTO, @PathVariable Long id) {
        log.info("Saving comment {} to blog with id {}", commentDTO, id);

        return new ResponseEntity<>(blogService.addCommentToBlog(commentDTO, id), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateBlog(@RequestBody BlogDto blogDTO) {
        log.info("Updating blog {}", blogDTO);
        blogService.updateBlog(blogDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBlog(@PathVariable Long id) {
        log.info("Deleting blog with id {}", id);
        blogService.deleteBlog(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

