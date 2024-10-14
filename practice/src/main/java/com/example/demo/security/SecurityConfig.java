package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    @Qualifier("myUserDetailsService")  // 使用するビーンを指定
    private MyUserDetailsService userDetailsService; // MyUserDetailsServiceをインジェクション

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf()
                .ignoringRequestMatchers("/admin/contacts/**")  // /admin/contacts のPOSTリクエストのCSRFを無効にする
                .ignoringRequestMatchers("/admin/logout")  // /admin/logout のPOSTリクエストのCSRFを無効にする
            .and()
            .formLogin()
                .loginPage("/admin/signin")
                .loginProcessingUrl("/admin/signin")
                .defaultSuccessUrl("/admin/contacts", true)
                .permitAll()
            .and()
            .logout()
                .logoutUrl("/admin/logout")
                .logoutSuccessUrl("/admin/signin")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll()
            .and()
            .authorizeHttpRequests()
                .requestMatchers("/admin/signin", "/admin/signup").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/admin/logout").permitAll()
                .anyRequest().authenticated();  // すべてのリクエストに対して認証を要求
        return http.build();
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = 
            http.getSharedObject(AuthenticationManagerBuilder.class);
        
        // 修正: userDetailsService を使用
        authenticationManagerBuilder.userDetailsService(userDetailsService)
                                     .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }
}
