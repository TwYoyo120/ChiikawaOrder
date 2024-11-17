//package com.chiikawa.order.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests()
//                .anyRequest().permitAll() // 允許所有請求訪問
//                .and()
//            .csrf().disable(); // 關閉 CSRF 防護（可選）
//        return http.build();
//    }
//}
//	
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////            .csrf(csrf -> csrf.disable())  // 停用 CSRF
////            .authorizeHttpRequests(authorize -> authorize
////                .requestMatchers(
////                    "/",                     // 根路徑
////                    "/login",                // 允許訪問登入頁面
////                    "/static/**",            // 允許 static 資源
////                    "/product/**",           // 允許 product 目錄下的所有資源
////                    "/css/**",               // 允許所有 CSS 檔案
////                    "/js/**",                // 允許所有 JS 檔案
////                    "/images/**",            // 允許圖片檔案
////                    "/**/*.html",            // 允許所有 HTML 文件
////                    "/favicon.ico"           // 允許訪問 favicon
////                ).permitAll()               // 允許訪問這些資源
////                .anyRequest().authenticated() // 其他請求需要驗證
////            );
////
////        return http.build();
////    }
//
