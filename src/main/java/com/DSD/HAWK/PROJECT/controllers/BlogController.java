package com.DSD.HAWK.PROJECT.controllers;

import com.DSD.HAWK.PROJECT.aggregator.Request.PostBlogRequest;
import com.DSD.HAWK.PROJECT.aggregator.Response.Blog.BlogResponse;
import com.DSD.HAWK.PROJECT.aggregator.Service.BlogAggregator;
import com.DSD.HAWK.PROJECT.model.Blog;
import com.DSD.HAWK.PROJECT.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BlogController {
    @Autowired
    BlogAggregator blogAggregator;
    @PostMapping(value = "/post/blog")
    public BlogResponse postBlog(@RequestBody PostBlogRequest postBlogRequest){
        return blogAggregator.postBlog(postBlogRequest);

    }
    @GetMapping(value = "/get/myBlogs")
    public List<Blog> getBlog(
            @RequestParam(value = "email",required = true)String email ){
        return blogAggregator.getAllBlogs(email);

    }



}
