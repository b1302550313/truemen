package com.sysu.verto.user.storage;

import com.sysu.verto.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByOpenId(String openId);
}