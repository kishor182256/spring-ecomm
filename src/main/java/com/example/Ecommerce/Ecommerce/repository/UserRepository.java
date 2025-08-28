package com.example.Ecommerce.Ecommerce.repository;

import com.example.Ecommerce.Ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    User findByEmail(String email);
}
