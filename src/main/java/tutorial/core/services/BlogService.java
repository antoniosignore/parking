package tutorial.core.services;

import tutorial.core.models.entities.Blog;
import tutorial.core.models.entities.BlogEntry;
import tutorial.core.services.util.BlogEntryList;
import tutorial.core.services.util.BlogList;

public interface BlogService {

    public BlogEntry createBlogEntry(Long blogId, BlogEntry data);

    public BlogList findAllBlogs();

    public BlogEntryList findAllBlogEntries(Long blogId); // findBlog all associated blog entries

    public Blog findBlog(Long id);

}
