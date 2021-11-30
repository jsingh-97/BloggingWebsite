package com.DSD.HAWK.PROJECT.repository;

import com.DSD.HAWK.PROJECT.model.Blog;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends CassandraRepository<Blog,String> {

}
