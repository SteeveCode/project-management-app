package com.brexson.projectmanagementapp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="users_seq")
    @SequenceGenerator(name = "users_seq", sequenceName = "users_seq",
            allocationSize = 1,initialValue=1)
    @Column(name="id")
    private long id;

    @Column(name="username", nullable=false, unique=true)
    private String userName;

    @Column(nullable=false)
    private String password;

    @Column(nullable=false)
    private String email;

    @Builder.Default
    private Boolean accountNonExpired = true;

    @Builder.Default
    private Boolean accountNonLocked = true;

    @Builder.Default
    private Boolean credentialsNonExpired = true;

    @Builder.Default
    private Boolean enabled = true;

    @Singular
    @ManyToMany(cascade=CascadeType.MERGE,fetch=FetchType.EAGER)
    @JoinTable(name="users_authorities",
            joinColumns={@JoinColumn(name="users_id",
                    referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="authorities_id",
                    referencedColumnName="id")})
    private Set<Authority> authorities;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "Users [username=" + userName + ", password=" + password + ", email=" + email + ", accountNonExpired="
                + accountNonExpired + ", accountNonLocked=" + accountNonLocked + ", credentialsNonExpired="
                + credentialsNonExpired + ", enabled=" + enabled + ", authorities=" + authorities + "]";
    }

}