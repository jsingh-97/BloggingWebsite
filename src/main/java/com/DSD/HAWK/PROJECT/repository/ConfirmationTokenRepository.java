package com.DSD.HAWK.PROJECT.repository;

import com.DSD.HAWK.PROJECT.model.ConfirmationToken;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;


import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
public interface ConfirmationTokenRepository extends CassandraRepository<ConfirmationToken,String> {
    ConfirmationToken findByToken(String token);

    @Query("UPDATE sessiontoken "+"SET confirmed_at = :time "+"WHERE \"token\" = :token")
    void setConfirmedAt(@Param("token") String token,@Param("time") LocalDateTime time);

}
