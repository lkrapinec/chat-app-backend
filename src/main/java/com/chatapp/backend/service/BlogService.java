package com.chatapp.backend.service;

import com.chatapp.backend.model.dto.BlogDTO;
import com.chatapp.backend.model.dto.CommentDTO;
import java.util.List;

public interface BlogService {
  BlogDTO getBlog(Long id);

  List<BlogDTO> getAllBlogs();

  BlogDTO createBlog(BlogDTO blogDTO);

  BlogDTO updateBlog(BlogDTO blogDTO);

  void deleteBlog(Long id);

  CommentDTO addCommentToBlog(CommentDTO commentDTO, Long id);
}
