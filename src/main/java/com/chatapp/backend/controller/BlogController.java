package com.chatapp.backend.controller;

import com.chatapp.backend.model.dto.BlogDTO;
import com.chatapp.backend.model.dto.CommentDTO;
import com.chatapp.backend.service.BlogService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/blog")
@Slf4j
public class BlogController {
  private final BlogService blogService;

  @Autowired
  public BlogController(BlogService blogService) {
    this.blogService = blogService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<BlogDTO> getBlog(@PathVariable Long id) {
    log.info("Get blog with id {}", id);

    return ResponseEntity.ok(blogService.getBlog(id));
  }

  @GetMapping
  public ResponseEntity<List<BlogDTO>> getAllBlogs() {
    log.info("Get all blogs");

    return ResponseEntity.ok(blogService.getAllBlogs());
  }

  @PostMapping
  public ResponseEntity<BlogDTO> createBlog(@RequestBody BlogDTO blogDTO) {
    log.info("Saving blog {}", blogDTO);

    return new ResponseEntity<>(blogService.createBlog(blogDTO), HttpStatus.CREATED);
  }

  @PostMapping("/{id}/comment")
  public ResponseEntity<CommentDTO> addCommentToBlog(@RequestBody CommentDTO commentDTO, @PathVariable Long id) {
    log.info("Saving comment {} to blog with id {}", commentDTO, id);

    return new ResponseEntity<>(blogService.addCommentToBlog(commentDTO, id), HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity<HttpStatus> updateBlog(@RequestBody BlogDTO blogDTO) {
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

