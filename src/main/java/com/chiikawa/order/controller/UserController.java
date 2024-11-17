//package com.chiikawa.order.controller;
//
//import com.chiikawa.order.model.User;
//import com.chiikawa.order.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import jakarta.servlet.http.HttpSession;
//
//@RestController
//@RequestMapping("/api/users")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    // 用户注册
//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@RequestBody User user) {
//        try {
//            boolean success = userService.registerUser(user);
//            if (success) {
//                return ResponseEntity.status(201).body("{\"message\":\"用户注册成功\"}");
//            } else {
//                return ResponseEntity.status(500).body("{\"error\":\"用户注册失败\"}");
//            }
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body("{\"error\":\"" + e.getMessage() + "\"}");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(500).body("{\"error\":\"无效的用户数据\"}");
//        }
//    }
//
//    // 用户登录
//    @GetMapping("/login")
//    public ResponseEntity<String> loginUser(
//            @RequestParam String username,
//            @RequestParam String password,
//            HttpSession session) {
//        if (username == null || password == null) {
//            return ResponseEntity.badRequest().body("{\"error\":\"缺少用户名或密码\"}");
//        }
//
//        try {
//            User user = userService.loginUser(username, password);
//            // 登录成功，将用户信息存储在 Session 中
//            session.setAttribute("userId", user.getUserId());
//            return ResponseEntity.ok("{\"message\":\"登录成功\"}");
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(401).body("{\"error\":\"" + e.getMessage() + "\"}");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(500).body("{\"error\":\"登录失败\"}");
//        }
//    }
//
//    // 用户登出
//    @DeleteMapping("/logout")
//    public ResponseEntity<String> logoutUser(HttpSession session) {
//        if (session != null) {
//            session.invalidate();
//        }
//        return ResponseEntity.ok("{\"message\":\"登出成功\"}");
//    }
//}
