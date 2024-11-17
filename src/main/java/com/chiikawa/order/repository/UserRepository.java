package com.chiikawa.order.repository;

import com.chiikawa.order.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // 根據用戶名查詢用戶
    User findByUsername(String username);
}
