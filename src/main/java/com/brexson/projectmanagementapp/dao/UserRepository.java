package com.brexson.projectmanagementapp.dao;

import com.brexson.projectmanagementapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface UserRepository extends JpaRepository <User, Long>{

    //	@Query(value="SELECT u FROM User u WHERE u.username = :username", nativeQuery=true)
    Optional<User> findByUserName(String username);

}
