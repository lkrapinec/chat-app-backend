package com.chatapp.backend.repository;

import com.chatapp.backend.model.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface CommentRepository extends JpaRepository<Comment, Long> {
  @Query(value = "SELECT blog.comments FROM Blog blog WHERE (:blogId is null or blog.id = :blogId)")
  List<Comment> findCommentByBlogId(@Param("blogId") Long blogId);

}
