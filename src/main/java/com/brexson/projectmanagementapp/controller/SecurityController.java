package com.brexson.projectmanagementapp.controller;

import com.brexson.projectmanagementapp.dao.UserRepository;
import com.brexson.projectmanagementapp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController {
    @Autowired
    UserRepository userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String register(Model model){
        User userAccount = new User();
        System.out.println(userAccount);

//	Set<Authorities> role = new HashSet<>();
//	role.add(roleAdmin);
//	userAccount.setAuthorities(role);
        model.addAttribute("userAccount", userAccount);
        return "security/register";
    }
    @PostMapping("/register/save")
    public String saveUser(Model model, User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//	user.setAuthorities(null);
        userRepo.save(user);
        return "redirect:/login";
    }
}

