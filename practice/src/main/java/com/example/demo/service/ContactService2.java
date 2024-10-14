package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Contact2;
import com.example.demo.repository.ContactRepository2;

@Service
public class ContactService2 {

    @Autowired
    private ContactRepository2 contactRepository2;

    // お問い合わせリストを取得
    public List<Contact2> getAllContacts() {
        return contactRepository2.findAll();
    }
}
