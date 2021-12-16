package com.DSD.HAWK.PROJECT.repository;

import com.DSD.HAWK.PROJECT.model.Blog;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends CassandraRepository<Blog,String> {

    @Query(value = "SELECT * from blog WHERE author_email=:email")
    List<Blog> findByEmail(@Param("email") String email);
}
