package com.DSD.HAWK.PROJECT.aggregator.Response.Blog;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class BlogResponse {
    private String id;

    private String email;

    private String title;

    private String content;
}
