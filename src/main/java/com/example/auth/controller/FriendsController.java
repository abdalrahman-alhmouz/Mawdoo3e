package com.example.auth.controller;

import com.example.auth.ApplicationUser;
import com.example.auth.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class FriendsController {
@Autowired
    ApplicationUserRepository applicationUserRepository;

    @GetMapping(value = "/allFriends")
    public String viewAllFriends(Principal p, Model m){
        ApplicationUser applicationUser=(ApplicationUser) ((UsernamePasswordAuthenticationToken)p).getPrincipal();
        m.addAttribute("friends",applicationUserRepository.findById(applicationUser.getId()).get());

        return "allFriends.html";
    }

    @GetMapping(value = "/allUsers")
    public String viewAllUsers(Principal p, Model m){
        ApplicationUser applicationUser=(ApplicationUser) ((UsernamePasswordAuthenticationToken)p).getPrincipal();
        m.addAttribute("users",applicationUserRepository.findById(applicationUser.getId()).get());
        m.addAttribute("allUsers",applicationUserRepository.findAll());

        return "allUsers.html";
    }
}
