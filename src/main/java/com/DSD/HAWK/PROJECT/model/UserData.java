package com.DSD.HAWK.PROJECT.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;


@Table(value= "userdata")
@Getter@Setter@AllArgsConstructor
public class UserData {
    @Column("name")
    private String name;
    @Id
    @Column("email")
    private String email;
    @Column("password")
    private String password;
    @Column("phone")
    private String phone;

    @Column("city")
    private String city;

    @Column("enabled")
    private boolean enabled;
    public UserData() {
    }


    public boolean getEnabled() {
        return enabled;
    }
}
