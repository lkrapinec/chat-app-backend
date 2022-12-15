package com.chatapp.backend.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.chatapp.backend.model.Blog;
import com.chatapp.backend.model.dto.BlogDTO;
import com.chatapp.backend.model.mapper.BlogMapper;
import com.chatapp.backend.repository.BlogRepository;
import com.chatapp.backend.service.impl.BlogServiceImpl;
import com.chatapp.backend.util.BlogGenerationgUtil;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BlogServiceImplTest {
  @Mock
  private BlogRepository blogRepository;

  @Mock
  private BlogMapper blogMapper;

  @InjectMocks
  private BlogServiceImpl blogService;

  @Test
  @DisplayName("Test get blog with valid id")
  void testGetBlogWithValidId() {
    //given
    Blog blog = BlogGenerationgUtil.generateBlog();
    BlogDTO blogDTO = BlogGenerationgUtil.generateBlogDTO();
    Long id = blog.getId();

    when(blogMapper.toDto(blog)).thenReturn(blogDTO);
    when(blogRepository.findById(id)).thenReturn(Optional.of(blog));

    //when
    blogService.getBlog(id);

    //then
    verify(blogMapper, times(1)).toDto(blog);
    verify(blogRepository, times(1)).findById(id);
    verifyNoMoreInteractions(blogMapper, blogRepository);
  }

  @Test
  @DisplayName("Test get blog with invalid id")
  void testGetBlogWithInvalidId() {
    //given
    Long id = 1L;

    doThrow(EntityNotFoundException.class).when(blogRepository).findById(id);

    //when
    Assertions.assertThrows(EntityNotFoundException.class, () -> blogService.getBlog(id));

    //then
    verify(blogRepository, times(1)).findById(id);
    verifyNoMoreInteractions(blogMapper, blogRepository);
  }

  @Test
  @DisplayName("Test get all blogs")
  void testGetAllBlogs() {
    //given
    Blog blog = BlogGenerationgUtil.generateBlog();
    BlogDTO blogDTO = BlogGenerationgUtil.generateBlogDTO();

    when(blogMapper.toDto(blog)).thenReturn(blogDTO);
    when(blogRepository.findAll()).thenReturn(List.of(blog));

    //when
    blogService.getAllBlogs();

    //then
    verify(blogMapper, times(1)).toDto(blog);
    verify(blogRepository, times(1)).findAll();
    verifyNoMoreInteractions(blogMapper, blogRepository);
  }

  @Test
  @DisplayName("Test save blog with valid blog")
  void testSaveBlogWithValidBlog() {
    //given
    List<Blog> blogs = BlogGenerationgUtil.generateBlogList();

    when(blogRepository.findAll()).thenReturn(blogs);

    //when
    blogService.getAllBlogs();

    //then
    verify(blogRepository, times(1)).findAll();
    verify(blogMapper, times(3)).toDto(any());
    verifyNoMoreInteractions(blogMapper, blogRepository);
  }

  @Test
  @DisplayName("Test save blog with invalid blog")
  void testSaveBlogWithInvalidBlog() {
    //given
    BlogDTO blogDTO = BlogGenerationgUtil.generateBlogDTO();

    //when
    Assertions.assertThrows(IllegalArgumentException.class, () -> blogService.createBlog(blogDTO));

    //then
    verifyNoInteractions(blogMapper, blogRepository);
  }

  @Test
  @DisplayName("Test update blog with valid blog")
  void testUpdateBlogWithValidBlog() {
    //given
    Blog blog = BlogGenerationgUtil.generateBlog();
    BlogDTO blogDTO = BlogGenerationgUtil.generateBlogDTO();
    Long id = blog.getId();

    when(blogRepository.findById(id)).thenReturn(Optional.of(blog));
    when(blogMapper.updateBlog(blogDTO, blog)).thenReturn(blog);
    when(blogRepository.save(blog)).thenReturn(blog);

    //when
    blogService.updateBlog(blogDTO);

    //then
    verify(blogRepository, times(1)).findById(id);
    verify(blogMapper, times(1)).updateBlog(blogDTO, blog);
    verify(blogRepository, times(1)).save(blog);
    verify(blogMapper, times(1)).toDto(blog);
    verifyNoMoreInteractions(blogMapper, blogRepository);
  }

  @Test
  @DisplayName("Test update blog with null id")
  void testUpdateBlogWithNullId() {
    //given
    BlogDTO blogDTO = BlogGenerationgUtil.generateBlogDTOWithoutId();

    //when
    Assertions.assertThrows(IllegalArgumentException.class, () -> blogService.updateBlog(blogDTO));

    //then
    verifyNoInteractions(blogMapper, blogRepository);
  }

  @Test
  @DisplayName("Test delete blog with valid id")
  void testDeleteBlogWithValidId() {
    //given
    Long id = 1L;

    doNothing().when(blogRepository).deleteById(id);

    //when
    blogService.deleteBlog(id);

    //then
    verify(blogRepository, times(1)).deleteById(id);
    verifyNoMoreInteractions(blogRepository);
  }

  @Test
  @DisplayName("Test delete blog with invalid id")
  void testDeleteBlogWithInvalidId() {
    //given
    Long id = 1L;

    doThrow(IllegalArgumentException.class).when(blogRepository).deleteById(id);

    //when
    Assertions.assertThrows(IllegalArgumentException.class, () -> blogService.deleteBlog(id));

    //then
    verify(blogRepository, times(1)).deleteById(id);
    verifyNoMoreInteractions(blogRepository);
  }
}
