package com.DSD.HAWK.PROJECT.aggregator.Service;

import com.DSD.HAWK.PROJECT.aggregator.Request.PostBlogRequest;
import com.DSD.HAWK.PROJECT.aggregator.Response.Blog.BlogResponse;
import com.DSD.HAWK.PROJECT.model.Blog;

import java.util.List;

public interface BlogAggregator {
    List<BlogResponse> getAllBlogs(String email);
    BlogResponse postBlog(PostBlogRequest postBlogRequest);

}
