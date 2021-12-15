package com.DSD.HAWK.PROJECT.aggregator.Response.Blog;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;

@Getter@Setter
public class BlogResponse {
    private String id;

    private String name;

    private String title;

    private String body;
}
