package com.DSD.HAWK.PROJECT.aggregator.Request;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class PostBlogRequest {
    private String email;
    private String title;
    private String content;
}
