package com.DSD.HAWK.PROJECT.aggregator.Service.Implementation;

import com.DSD.HAWK.PROJECT.aggregator.Request.PostBlogRequest;
import com.DSD.HAWK.PROJECT.aggregator.Response.Blog.BlogResponse;
import com.DSD.HAWK.PROJECT.aggregator.Service.BlogAggregator;
import com.DSD.HAWK.PROJECT.model.Blog;
import com.DSD.HAWK.PROJECT.repository.Service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BlogAggregatorImpl implements BlogAggregator {
    @Autowired
    BlogService blogService;
    @Override
    public List<Blog> getAllBlogs(String email) {

        return blogService.getAllBlogs(email);
    }

    @Override
    public BlogResponse postBlog(PostBlogRequest postBlogRequest) {
        String id = UUID.randomUUID().toString();
        Blog newBlog = new Blog(id,postBlogRequest.getEmail(),postBlogRequest.getBlogTitle(),postBlogRequest.getBlogBody());
        blogService.postBlog(newBlog);
        BlogResponse blogResponse = new BlogResponse();
        blogResponse.setBody("Blog uploaded successfully");
        return blogResponse;
    }
}
