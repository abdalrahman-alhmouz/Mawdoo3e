package com.example.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

        import java.security.Principal;
import java.util.List;
import java.util.Set;

import static org.hibernate.internal.util.collections.ArrayHelper.toList;

@Controller
public class ApplicationUserController {
@Autowired
PostReposritry postReposritry;
    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    CommentsRepository commentsRepository;

    @GetMapping("/signup")
    public String getSignUpPage(){
        return "signup.html";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "logIn.html";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam(value="username") String username,
                               @RequestParam(value="password") String password,
                               @RequestParam(value="firstName") String firstName,
                               @RequestParam(value="lastName") String lastName,
                               @RequestParam(value="dateOfBirth") String dateOfBirth,
                               @RequestParam(value="bio") String bio){
        ApplicationUser newUser = new ApplicationUser(username,bCryptPasswordEncoder.encode(password),firstName,lastName,dateOfBirth,bio);
        newUser = applicationUserRepository.save(newUser);
        return "logIn.html";
    }


    @GetMapping("/myprofile")
    public String getUserProfilePage(Principal p,Model m){
        ApplicationUser applicationUser=(ApplicationUser) ((UsernamePasswordAuthenticationToken)p).getPrincipal();
//        Set<ApplicationUser> friends = applicationUser.getFriends();
//        m.addAttribute("friends",friends);
//        System.out.println(commentsRepository.findByPostApplicationId(7L).get(0).getCommentBody());
        m.addAttribute("user",applicationUserRepository.findById(applicationUser.id).get() );
    return "profilePage.html";
    }

    @GetMapping("/AddPostt")
    public String addpostt(Principal p,Model m){
        ApplicationUser applicationUser=(ApplicationUser) ((UsernamePasswordAuthenticationToken)p).getPrincipal();
        m.addAttribute("user",applicationUserRepository.findById(applicationUser.id).get() );
        return "profilePage.html";
    }

    @PostMapping("/AddPost")
    public RedirectView addPost(@RequestParam String body,Principal p){
        ApplicationUser userDetails = (ApplicationUser) ((UsernamePasswordAuthenticationToken) p).getPrincipal();
        PostApplication postPage=new PostApplication(body,userDetails);
        postReposritry.save(postPage);
        return new RedirectView("/AddPostt");
    }


@GetMapping("/view/{id}")
public String getAlbum(@PathVariable(value="id")Integer id ,Model m,Principal p){
    ApplicationUser user=applicationUserRepository.findById(id).get();
    m.addAttribute("user",user);

    ApplicationUser applicationUser=(ApplicationUser) ((UsernamePasswordAuthenticationToken)p).getPrincipal();
    m.addAttribute("userme",applicationUserRepository.findById(applicationUser.id).get() );

    Iterable<ApplicationUser> allUsers = applicationUserRepository.findAll();
    m.addAttribute("allUsers",allUsers);


    return "nonFriend.html";
}
    @GetMapping("/friends/{id}")
    public RedirectView getMyProfile(@PathVariable Integer id, Model m, Principal p) {
        ApplicationUser friend = applicationUserRepository.findById(id).get();
        //get current user
        ApplicationUser currentUser = applicationUserRepository.findByUsername(p.getName());
        Set<ApplicationUser> followFriends = currentUser.getFriends();
        if (followFriends.contains(friend)) {
            m.addAttribute("principal", friend);
            return new RedirectView("/displayFriendPost/"+id);
        }
        return new RedirectView("/view/"+id);
    }
    @GetMapping("/displayFriendPost/{id}")
    public String displayFriendPost(@PathVariable Integer id, Model m, Principal p) {
        ApplicationUser friend = applicationUserRepository.findById(id).get();
            m.addAttribute("principal", friend);
        return "friendsPage.html";
    }

    @PostMapping("/AddFriend")
    public RedirectView addFriend(@RequestParam(value = "id") Integer id,@RequestParam(value = "friend") Integer friend) {
        ApplicationUser curfriend = applicationUserRepository.findById(id).get();
        ApplicationUser newfriend = applicationUserRepository.findById(friend).get();
        curfriend.friends.add(newfriend);
        newfriend.friends.add(curfriend);

        applicationUserRepository.save(curfriend);
        applicationUserRepository.save(newfriend);

        return new RedirectView("/friends/" + id);
    }
    @GetMapping(value = "/feed")
    public String getFeed(Model m, Principal p) {
        //get current user
        ApplicationUser currentUser = applicationUserRepository.findByUsername(p.getName());
        Set<ApplicationUser> followFriends = currentUser.getFriends();
        System.out.println(followFriends.size()+"sssssssssssssssssssssss");
        m.addAttribute("followingFriends", followFriends);
        m.addAttribute("users", currentUser);
        //for the nav bar
        m.addAttribute("myProfile", true);
        m.addAttribute("user", true);

        return "displayPost.html";
    }

}
