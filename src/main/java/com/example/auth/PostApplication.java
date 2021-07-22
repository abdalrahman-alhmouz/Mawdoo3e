package com.example.auth;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class PostApplication {
    private String body;
    @ManyToOne
    private  ApplicationUser applicationUser ;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt")
    private Date createdAt;

//    @OneToMany(mappedBy = "PostApplication", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<CommentsApplication> comments;
//
//    public List<CommentsApplication> getComments() {
//        return comments;
//    }
//
//    public void setComments(List<CommentsApplication> comments) {
//        this.comments = comments;
//    }


    public PostApplication(String body, ApplicationUser applicationUser) {
        this.body = body;
        this.applicationUser=applicationUser;
    }
    public PostApplication() {
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    public Integer getId() {
        return id;
    }


}
