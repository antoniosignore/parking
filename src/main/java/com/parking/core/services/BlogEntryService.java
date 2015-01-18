package com.parking.core.services;

import com.parking.core.models.entities.BlogEntry;

public interface BlogEntryService {

    public BlogEntry findBlogEntry(Long id);

    public BlogEntry deleteBlogEntry(Long id);

    public BlogEntry updateBlogEntry(Long id, BlogEntry data);

}
