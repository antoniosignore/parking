package com.parking.core.repositories;

import com.parking.core.models.entities.BlogEntry;

import java.util.List;

public interface BlogEntryRepo {

    public BlogEntry findBlogEntry(Long id);

    public BlogEntry deleteBlogEntry(Long id);

    public BlogEntry updateBlogEntry(Long id, BlogEntry data);

    public BlogEntry createBlogEntry(BlogEntry data);

    public List<BlogEntry> findByBlogId(Long blogId);

}
