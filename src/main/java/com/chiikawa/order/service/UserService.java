//package com.chiikawa.order.service;
//
//import com.chiikawa.order.model.User;
//import com.chiikawa.order.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
////public class UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
//
//    // 用户注册
//    public boolean registerUser(User user) {
//        // 檢查用戶名是否已存在
//        if (userRepository.findByUsername(user.getUsername()) != null) {
//            throw new IllegalArgumentException("用户名已存在");
//        }
//
//        // 加密密碼
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        // 保存用戶
//        userRepository.save(user);
//        return true;
//    }
//
//    // 用户登录
//    public User loginUser(String username, String password) {
//        User user = userRepository.findByUsername(username);
//        if (user == null) {
//            throw new IllegalArgumentException("用户不存在");
//        }
//
//        // 驗證密碼
//        if (!passwordEncoder.matches(password, user.getPassword())) {
//            throw new IllegalArgumentException("密码错误");
//        }
//
//        return user;
//    }
//}
