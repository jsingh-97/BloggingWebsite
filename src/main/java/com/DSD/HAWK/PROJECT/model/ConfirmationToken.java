package com.DSD.HAWK.PROJECT.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;

@Table(value= "sessiontoken")
@Getter@Setter@AllArgsConstructor
public class ConfirmationToken {
    @Id
    @Column("token")
    private String token;
    @Column("created_at")
    private LocalDateTime createdAt;
    @Column("expires_at")
    private LocalDateTime expiresAt;
    @Column("confirmed_at")
    private LocalDateTime confirmedAt;
    @Column("email")
    private String email;

}