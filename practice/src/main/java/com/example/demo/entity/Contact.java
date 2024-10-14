package com.example.demo.entity;

import java.time.LocalDateTime;  // java.time.LocalDateTimeを使う

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // お問い合わせID
    
    @Column(name = "last_name", nullable = false)
    private String lastName;  // 姓
    
    @Column(name = "first_name", nullable = false)
    private String firstName;  // 名
    
    @Column(name = "email", nullable = false)
    private String email;  // メールアドレス
    
    @Column(name = "phone", nullable = false)
    private String phone;  // 電話番号
    
    @Column(name = "zip_code", nullable = false)
    private String zipCode;  // 郵便番号
    
    @Column(name = "address", nullable = false)
    private String address;  // 住所
    
    @Column(name = "building_name", nullable = false)
    private String buildingName;  // 建物名
    
    @Column(name = "contact_type", nullable = false)
    private String contactType;  // お問い合わせ種別
    
    @Column(name = "body", nullable = false)
    private String body;  // 内容
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;  // 作成日時（LocalDateTimeに変更）
    
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;  // 更新日時（命名変更）

    // デフォルトコンストラクタ
    public Contact() {
    }

    // 引数付きコンストラクタ
    public Contact(String lastName, String firstName, String email, String phone, String zipCode, String address, 
                     String buildingName, String contactType, String body, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phone = phone;
        this.zipCode = zipCode;
        this.address = address;
        this.buildingName = buildingName;
        this.contactType = contactType;
        this.body = body;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
