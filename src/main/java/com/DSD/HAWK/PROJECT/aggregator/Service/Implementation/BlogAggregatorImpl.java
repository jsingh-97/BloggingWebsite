package com.DSD.HAWK.PROJECT.aggregator.Service.Implementation;

import com.DSD.HAWK.PROJECT.aggregator.Request.PostBlogRequest;
import com.DSD.HAWK.PROJECT.aggregator.Response.Blog.BlogResponse;
import com.DSD.HAWK.PROJECT.aggregator.Service.BlogAggregator;
import com.DSD.HAWK.PROJECT.lib.Constant;
import com.DSD.HAWK.PROJECT.model.Blog;
import com.DSD.HAWK.PROJECT.repository.Service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BlogAggregatorImpl implements BlogAggregator {
    @Autowired
    BlogService blogService;
    @Override
    public List<BlogResponse> getAllBlogs(String email) {
        List<BlogResponse> ls = new ArrayList<>();
        try {
            List<Blog> blogs = blogService.getAllBlogs(email);
            blogs.stream().forEach(blog -> ls.add(new BlogResponse(blog.getId(),blog.getEmail(),blog.getBlogTitle(), blog.getBlogBody())));
            return ls;
        }catch (Exception ex){
            System.out.println("Unable to fetch info from database");
            return Constant.blogList;
        }

    }

    @Override
    public BlogResponse postBlog(PostBlogRequest postBlogRequest) {
        String id = UUID.randomUUID().toString();
        Blog newBlog = new Blog(id,postBlogRequest.getEmail(),postBlogRequest.getTitle(),postBlogRequest.getContent());
        blogService.postBlog(newBlog);
        BlogResponse blogResponse = new BlogResponse();
        blogResponse.setContent("Blog uploaded successfully");
        return blogResponse;
    }
}
