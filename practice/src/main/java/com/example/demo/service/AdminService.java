package com.example.demo.service;

import java.sql.Timestamp;  // Timestamp をインポート
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admins;
import com.example.demo.model.Admin;
import com.example.demo.repository.AdminsRepository;

@Service
public class AdminService {

    @Autowired
    private AdminsRepository adminsRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void save(Admin admin) {
        // パスワードをエンコード
        String encryptedPassword = passwordEncoder.encode(admin.getPassword());

        // Admin エンティティから admins エンティティに変換
        Admins newAdmin = new Admins();
        newAdmin.setLastName(admin.getLastName());  // Lombok が自動生成した setter を使っている
        newAdmin.setFirstName(admin.getFirstName());
        newAdmin.setEmail(admin.getEmail());
        newAdmin.setPassword(encryptedPassword);

        // 現在の時刻を設定
        newAdmin.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));  // LocalDateTime を Timestamp に変換

        // DBに保存
        adminsRepository.save(newAdmin);  // エンティティをリポジトリに保存
    }
}