package com.uncledemy.the_thinking_space.repo;



import com.uncledemy.the_thinking_space.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {


    Optional<User> findUserByEmail(String email);

    User findByEmail(String username);

}
