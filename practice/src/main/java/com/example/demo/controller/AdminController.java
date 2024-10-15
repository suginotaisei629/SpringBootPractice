package com.example.demo.controller;

import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Contact;
import com.example.demo.model.Admin;
import com.example.demo.model.AdminLogin;
import com.example.demo.model.Contact2;
import com.example.demo.service.AdminService;
import com.example.demo.service.ContactServiceId;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin/signup"; // signup.htmlを表示
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute Admin admin) {
        adminService.save(admin);
        return "redirect:/admin/contacts"; // 登録後にリダイレクト
    }

    @GetMapping("/signin")
    public String signinForm(Model model) {
        model.addAttribute("adminLogin", new AdminLogin());
        return "admin/signin"; // signin.htmlを表示
    }

    @GetMapping("/contacts")
    public String showContactForm(Model model) {
        model.addAttribute("contact2", new Contact2()); // 新しいContact2オブジェクトを作成
        return "admin/contacts"; // contacts.htmlを返す
    }
    
    @PostMapping("/contacts")
    public String handleContactForm(@ModelAttribute Contact contact) {
        Long id = contact.getId();
        if (id != null) {
            // IDが存在する場合、詳細ページにリダイレクト
            return "redirect:/admin/contacts/" + id;
        } else {
            // IDがない場合、もしくは他の処理をしたい場合
            return "redirect:/admin/contacts";
        }
    }
    
    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/admin/signin";  // ログアウト後のリダイレクト先
    }
    
    @Autowired
    private ContactServiceId contactServiceId;

 // 詳細表示
    @GetMapping("/contacts/{id}")
    public String showContactDetails(@PathVariable("id") Long id, Model model) {
        Optional<Contact> contactOptional = contactServiceId.findById(id);

        if (contactOptional.isPresent()) {
        	 Contact contact = contactOptional.get();  // 連絡先が見つかった場合
             model.addAttribute("contact", contact); // 検索結果をcontactIdとしてモデルにセット
            return "admin/contact-details";  // 詳細ページ用のテンプレートを表示
        } else {
            model.addAttribute("message", "お問い合わせが見つかりませんでした。");
            return "admin/contact-not-found";  // 詳細が見つからなかった場合のエラーページ
        }
    }
    
    
  
    
    @GetMapping("/contacts/{id}/edit")
    public String editContact(@PathVariable("id") Long id, Model model) {
        Optional<Contact> contactOptional = contactServiceId.findById(id);
        
        if (contactOptional.isPresent()) {
            model.addAttribute("contact", contactOptional.get());
            return "admin/edit-contact";  // 編集フォームを表示するビュー
        } else {
            return "redirect:/admin/contacts";  // 見つからなかった場合のリダイレクト
        }
    }
    
    @PostMapping("/contacts/{id}/delete")
    public String deleteContact(@PathVariable("id") Long id) {
        contactServiceId.deleteById(id);  // IDを指定して削除
        return "redirect:/admin/contacts";  // 削除後、一覧に戻す
    }

    
}
