package com.chatapp.backend.model.mapper;

import com.chatapp.backend.model.Blog;
import com.chatapp.backend.model.Comment;
import com.chatapp.backend.model.dto.BlogDto;
import com.chatapp.backend.model.dto.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class BlogMapper {
    private final CommentMapper commentMapper;

    @Autowired
    public BlogMapper(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public BlogDto toDto(Blog blog) {
        List<CommentDto> commentDtos = null;
        if (blog.getComments() != null && !blog.getComments().isEmpty()) {
            commentDtos = blog.getComments().stream().map(commentMapper::toDto).toList();
        }

        return new BlogDto(
                blog.getId(),
                blog.getTitle(),
                blog.getDescription(),
                blog.getText(),
                blog.getDateOfCreation(),
                blog.getDateOfUpdate(),
                blog.getAuthor(),
                commentDtos
        );
    }

    public Blog fromDto(BlogDto blogDTO) {
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

    public Blog updateBlog(BlogDto blogDTO, Blog blog) {
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
