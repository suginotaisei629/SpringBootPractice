package com.example.demo.entity;

import java.sql.Timestamp;  // 修正: java.sql.Timestampをインポート

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Admins {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lastName;
    private String firstName;
    private String email;
    private String password;
    private Timestamp currentSignInAt;  // ここも Timestamp を使用
    private Timestamp createdAt;        // ここも Timestamp を使用
    private Timestamp updateAt;         // ここも Timestamp を使用

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = new Timestamp(System.currentTimeMillis());  // 現在時刻を設定
        }
        if (updateAt == null) {
            updateAt = new Timestamp(System.currentTimeMillis());  // 現在時刻を設定
        }
    }

    @PreUpdate
    public void preUpdate() {
        updateAt = new Timestamp(System.currentTimeMillis());
    }
}
