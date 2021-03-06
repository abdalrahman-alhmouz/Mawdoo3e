package com.example.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
public class ApplicationUser implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    private String password;
    @Column(unique = true)
    private String username;
    private String bio;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String post;


    @OneToMany(mappedBy = "applicationUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PostApplication> posts;

    @OneToMany(mappedBy = "applicationUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CommentsApplication> comments;

    public List<CommentsApplication> getComments() {
        return comments;
    }

    public void setComments(List<CommentsApplication> comments) {
        this.comments = comments;
    }

    @ManyToMany
    Set<ApplicationUser> friends;

    public ApplicationUser(){

    }



    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public ApplicationUser(String username, String password, String firstName, String lastName, String dateOfBirth, String bio) {
        this.password = password;
        this.username = username;
        this.bio = bio;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public Integer getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "ApplicationUser{" +
                "userName='" + this.username + '\'' +
                '}';
    }

    public List<PostApplication> getPosts() {
        return posts;
    }

    public void setPosts(List<PostApplication> posts) {
        this.posts = posts;
    }


    public Set<ApplicationUser> getFriends() { return friends; }

    public void setFriends(Set<ApplicationUser> friends) { this.friends = friends; }
}
