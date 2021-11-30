package com.DSD.HAWK.PROJECT.controllers;

import com.DSD.HAWK.PROJECT.model.Blog;
import com.DSD.HAWK.PROJECT.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class BlogController {
    @Autowired
    BlogRepository blogRepository;
    @PostMapping(value = "/post")
    public String postBlog(){
        System.out.print("Application started!!");
        Blog blog = new Blog();
        blog.setId("id");
        blog.setBlogTitle("First blog");
        blog.setName("First name");
        blog.setBlogBody("First blog body");
        blogRepository.save(blog);
        return "Hello Blog is here";
    }
    @GetMapping(value = "/get")
    public Blog getBlog(){
        Optional<Blog> blogOptional = blogRepository.findById("id");
        return blogOptional.get();
    }

    @PutMapping(value = "/put")
    public Blog updateBlog(){
        Blog blog = new Blog();
        blog.setId("id");
        blog.setBlogTitle("First blog");
        blog.setName("First name");
        blog.setBlogBody("First blog body");
        blogRepository.save(blog);
        return blog;
    }

    @DeleteMapping(value = "/delete")
    public Blog deleteBlog(@RequestParam(value = "id") String id){
        Optional<Blog> blog = blogRepository.findById(id);
        blogRepository.deleteById("id");
        return blog.get();
    }

}
