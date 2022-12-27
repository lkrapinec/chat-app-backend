package com.chatapp.backend.util;

import com.chatapp.backend.model.Blog;
import com.chatapp.backend.model.dto.BlogDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BlogGenerationgUtil {
    private static final LocalDateTime DATE_TIME = LocalDateTime.parse("2018-12-30T19:34:50.63");

    public static Blog generateBlog() {
        return new Blog(
                1L,
                "Title",
                "description",
                "text",
                DATE_TIME,
                DATE_TIME,
                "author",
                new ArrayList<>());
    }

    public static BlogDto generateBlogDTO() {
        return new BlogDto(
                1L,
                "Title",
                "description",
                "text",
                DATE_TIME,
                DATE_TIME,
                "author",
                new ArrayList<>());
    }

    public static BlogDto generateBlogDTOWithoutId() {
        return new BlogDto(
                null,
                "Title",
                "description",
                "text",
                DATE_TIME,
                DATE_TIME,
                "author",
                new ArrayList<>());
    }

    public static List<Blog> generateBlogList() {
        List<Blog> blogs = new ArrayList<>();

        for (int i = 1; i < 4; i++) {
            blogs.add(new Blog(
                    (long) i,
                    "Title " + i,
                    "description " + i,
                    "text" + i,
                    DATE_TIME,
                    DATE_TIME,
                    "author",
                    new ArrayList<>()));
        }

        return blogs;
    }
}
