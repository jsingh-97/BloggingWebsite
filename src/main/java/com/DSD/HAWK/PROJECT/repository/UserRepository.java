package com.DSD.HAWK.PROJECT.repository;

import com.DSD.HAWK.PROJECT.model.UserData;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository

public interface UserRepository extends CassandraRepository<UserData,String> {


    @Query(value = "UPDATE userdata "+"SET enabled = TRUE WHERE email=:email")
    void verifyUser(@Param("email") String email);
}
