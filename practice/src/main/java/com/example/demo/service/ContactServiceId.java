package com.example.demo.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Contact;
import com.example.demo.repository.ContactRepositoryId;

@Service
public class ContactServiceId {

    @Autowired
    private ContactRepositoryId contactRepositoryId;

    public Optional<Contact> findById(Long id) {
        return contactRepositoryId.findById(id); // JpaRepository の標準メソッドを使用
    }
    
    public void deleteById(Long id) {
        contactRepositoryId.deleteById(id);  // JpaRepository の削除メソッドを使用
    }

}

