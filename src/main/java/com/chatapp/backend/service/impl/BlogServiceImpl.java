package com.chatapp.backend.service.impl;

import com.chatapp.backend.model.Blog;
import com.chatapp.backend.model.dto.BlogDto;
import com.chatapp.backend.model.dto.CommentDto;
import com.chatapp.backend.model.mapper.BlogMapper;
import com.chatapp.backend.model.mapper.CommentMapper;
import com.chatapp.backend.repository.BlogRepository;
import com.chatapp.backend.service.BlogService;
import com.chatapp.backend.service.CommentService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;

    private final BlogMapper blogMapper;

    private final CommentMapper commentMapper;

    private final CommentService commentService;

    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository, BlogMapper blogMapper, CommentMapper commentMapper, CommentService commentService) {
        this.blogRepository = blogRepository;
        this.blogMapper = blogMapper;
        this.commentMapper = commentMapper;
        this.commentService = commentService;
    }

    @Override
    public BlogDto getBlog(Long id) {
        return blogRepository.findById(id).map(blogMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Blog with id " + id + " not found"));
    }

    @Override
    public List<BlogDto> getAllBlogs() {
        return blogRepository.findAll().stream().map(blogMapper::toDto).toList();
    }

    @Override
    public BlogDto createBlog(BlogDto blogDTO) {
        if (blogDTO.getId() != null) {
            throw new IllegalArgumentException("Blog id must be empty");
        }
        Blog blog = blogMapper.fromDto(blogDTO);
        blog.setDateOfCreation(LocalDateTime.now());
        blog.setDateOfUpdate(LocalDateTime.now());
        blog = blogRepository.save(blog);

        return blogMapper.toDto(blog);
    }

    @Transactional
    @Override
    public CommentDto addCommentToBlog(CommentDto commentDTO, Long id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Blog with id " + id + " not found"));

        commentDTO = commentService.saveComment(commentDTO, id);
        blog.getComments().add(commentMapper.fromDto(commentDTO));

        blogRepository.save(blog);

        return commentDTO;
    }

    @Override
    public BlogDto updateBlog(BlogDto blogDTO) {
        if (blogDTO.getId() == null) {
            throw new IllegalArgumentException("Blog id must not be empty");
        }
        Blog blog = blogRepository.findById(blogDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Blog does not exist"));

        blog = blogMapper.updateBlog(blogDTO, blog);
        blog = blogRepository.save(blog);

        return blogMapper.toDto(blog);
    }

    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
