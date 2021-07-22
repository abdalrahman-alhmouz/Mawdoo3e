package com.example.auth.controller;

import com.example.auth.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@Controller
public class CommentsController {

    @Autowired
    CommentsRepository commentsRepository ;
    @Autowired
    PostReposritry postReposritry;
    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @PostMapping("/AddComment")
    public RedirectView addComment(@RequestParam String Comment,@RequestParam(value = "postId") Integer postId, Model m, Principal p){
        ApplicationUser userDetails = (ApplicationUser) ((UsernamePasswordAuthenticationToken) p).getPrincipal();
        PostApplication postApplication=postReposritry.findById(postId).get();
        CommentsApplication postPage=new CommentsApplication(Comment,userDetails,postApplication);
        commentsRepository.save(postPage);
                ApplicationUser applicationUser=(ApplicationUser) ((UsernamePasswordAuthenticationToken)p).getPrincipal();
        m.addAttribute("user",applicationUserRepository.findById(applicationUser.getId()).get() );
        List<CommentsApplication> listComment=commentsRepository.findByPostApplicationId(Long.valueOf(postId));
//    m.addAttribute("postComments", );
                return new RedirectView("/viewComment/"+postId);
    }

    @GetMapping("/viewComment/{id}")
    public String addpostt(@PathVariable (value="id")Long id , Principal p, Model m){
        ApplicationUser applicationUser=(ApplicationUser) ((UsernamePasswordAuthenticationToken)p).getPrincipal();
        m.addAttribute("users",applicationUserRepository.findById(applicationUser.getId()).get() );
        m.addAttribute("post",postReposritry.findById(id.intValue()).get());
        m.addAttribute("postComments", commentsRepository.findByPostApplicationId(id));
    return "commentsPage.html";
    }


}
