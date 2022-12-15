package com.chatapp.backend.util;

import com.chatapp.backend.model.Comment;
import com.chatapp.backend.model.dto.CommentDTO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CommentGenerationgUtil {
  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

  public static Comment generateComment() {
    return new Comment(
        1L,
        "This is first comment.",
        LocalDateTime.parse("2015-08-04 12:30", formatter),
        LocalDateTime.parse("2017-09-03 18:30", formatter),
        "author 1");
  }

  public static CommentDTO generateCommentDTO() {
    return new CommentDTO(
        1L,
        "This is first comment.",
        LocalDateTime.parse("2015-08-04 12:30", formatter),
        LocalDateTime.parse("2017-09-03 18:30", formatter),
        "author 1");
  }

  public static CommentDTO generateCommentDTOWithoutId() {
    return new CommentDTO(
        null,
        "This is first comment.",
        LocalDateTime.parse("2015-08-04 12:30", formatter),
        LocalDateTime.parse("2017-09-03 18:30", formatter),
        "author 1");
  }

  public static List<Comment> generateCommentList() {
    List<Comment> comments = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      comments.add(new Comment(
          (long) i,
          "This is comment number " + i,
          LocalDateTime.parse("2015-08-04 12:30", formatter),
          LocalDateTime.parse("2017-09-03 18:30", formatter),
          "author " + i
      ));
    }

    return comments;
  }
}
