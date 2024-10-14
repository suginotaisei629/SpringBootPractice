package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Contact2;

@Repository
public interface ContactRepository2 extends JpaRepository<Contact2, Long> {
    // 必要なカスタムメソッドをここに定義することができます
}
