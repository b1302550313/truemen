package com.truemen.api.user.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truemen.api.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUserId(String userId);
}