package com.chatapp.backend.service;

import com.chatapp.backend.model.dto.BlogDto;
import com.chatapp.backend.model.dto.CommentDto;

import java.util.List;

public interface BlogService {
    BlogDto getBlog(Long id);

    List<BlogDto> getAllBlogs();

    BlogDto createBlog(BlogDto blogDTO);

    BlogDto updateBlog(BlogDto blogDTO);

    void deleteBlog(Long id);

    CommentDto addCommentToBlog(CommentDto commentDTO, Long id);
}
