package com.brexson.projectmanagementapp.service;

import com.brexson.projectmanagementapp.dao.UserRepository;
import com.brexson.projectmanagementapp.entities.Authority;
import com.brexson.projectmanagementapp.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username).get();
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                getAuthorities(user));
    }

    // Custom function to help retrieve all the authorities of a given user
    private Set<GrantedAuthority> getAuthorities(User user) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Authority authority : user.getAuthorities()) {
            authorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }
        return authorities;
    }

}
