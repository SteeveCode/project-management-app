package com.brexson.projectmanagementapp.entities;

import jakarta.persistence.*;
import lombok.NonNull;

//@Builder
@Entity
@Table(name="authorities")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="authorities_seq")
    @SequenceGenerator(name = "authorities_seq", sequenceName = "authorities_seq",
            allocationSize = 1,initialValue=1)
    private int id;
    @Column(unique=true)
    private String authority;
    public int getId() {
        return id;
    }
    public Authority() {
    }
    public Authority(@NonNull String authority) {
        this.authority = authority;
    }
    public Authority(Integer id, @NonNull String authority) {
        super();
        this.id = id;
        this.authority = authority;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
