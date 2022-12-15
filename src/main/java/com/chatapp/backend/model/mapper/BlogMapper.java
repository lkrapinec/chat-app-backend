package com.chatapp.backend.model.mapper;

import com.chatapp.backend.model.Blog;
import com.chatapp.backend.model.Comment;
import com.chatapp.backend.model.dto.BlogDTO;
import com.chatapp.backend.model.dto.CommentDTO;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BlogMapper {
  private final CommentMapper commentMapper;

  @Autowired
  public BlogMapper(CommentMapper commentMapper) {
    this.commentMapper = commentMapper;
  }

  public BlogDTO toDto(Blog blog) {
    List<CommentDTO> commentDTOS = null;
    if (blog.getComments() != null && !blog.getComments().isEmpty()) {
      commentDTOS = blog.getComments().stream().map(commentMapper::toDto).toList();
    }

    return new BlogDTO(
        blog.getId(),
        blog.getTitle(),
        blog.getDescription(),
        blog.getText(),
        blog.getDateOfCreation(),
        blog.getDateOfUpdate(),
        blog.getAuthor(),
        commentDTOS
    );
  }

  public Blog fromDto(BlogDTO blogDTO) {
    List<Comment> comments = null;
    if (blogDTO.getComments() != null && !blogDTO.getComments().isEmpty()) {
      comments = blogDTO.getComments().stream().map(commentMapper::fromDto).toList();
    }

    return new Blog(
        blogDTO.getId(),
        blogDTO.getTitle(),
        blogDTO.getDescription(),
        blogDTO.getText(),
        blogDTO.getDateOfCreation(),
        blogDTO.getDateOfUpdate(),
        blogDTO.getAuthor(),
        comments
    );
  }

  public Blog updateBlog(BlogDTO blogDTO, Blog blog) {
    if (blogDTO.getTitle() != null) {
      blog.setTitle(blogDTO.getTitle());
    }
    if (blogDTO.getDescription() != null) {
      blog.setDescription(blogDTO.getDescription());
    }
    blog.setDateOfUpdate(LocalDateTime.now());

    return blog;
  }
}
