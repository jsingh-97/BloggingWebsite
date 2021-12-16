package com.DSD.HAWK.PROJECT.repository.Service;

import com.DSD.HAWK.PROJECT.aggregator.Response.Blog.BlogResponse;
import com.DSD.HAWK.PROJECT.model.Blog;
import com.DSD.HAWK.PROJECT.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository;

    public List<Blog> getAllBlogs(String email){
        List<Blog> blogList = new ArrayList();
        blogList = blogRepository.findAll();
        List<Blog> ls = new ArrayList<>();
        ls = blogList.stream().filter(blog -> blog.getEmail().equals(email)).collect(Collectors.toList());
        return ls;
    }
    public void postBlog(Blog blog){
        try {
            blogRepository.save(blog);
        }catch (Exception ex){
            System.out.println("Post not uploaded :"+ex);
        }
    }

}
