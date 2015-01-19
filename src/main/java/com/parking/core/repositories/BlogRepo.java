package com.parking.core.repositories;

import com.parking.core.models.entities.Blog;

import java.util.List;

public interface BlogRepo {

    public Blog createBlog(Blog data);

    public List<Blog> findAllBlogs();

    public Blog findBlog(Long id);

    public Blog findBlogByTitle(String title);

    public List<Blog> findBlogsByAccountId(Long accountId);

}
