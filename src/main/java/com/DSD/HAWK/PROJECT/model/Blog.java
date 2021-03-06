package com.DSD.HAWK.PROJECT.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(value = "blog")
@Getter@Setter@AllArgsConstructor
public class Blog {
    @Id@PrimaryKeyColumn(name = "blog_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String id;
    @Column("author_email")
    @CassandraType(type= CassandraType.Name.TEXT)
    private String email;
    @Column("blog_title")
    private String blogTitle;
    @Column("blog_body")
    private String blogBody;

}
