package com.parking.core.services;

import com.parking.core.models.entities.Blog;
import com.parking.core.services.util.BlogEntryList;
import com.parking.core.services.util.BlogList;

public interface BlogService {

    public BlogList findAllBlogs();

    public BlogEntryList findAllBlogEntries(Long blogId); // findBlog all associated blog entries

    public Blog findBlog(Long id);

}
