package com.example.auth;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CommentsApplication {
    public String commentBody;
    @ManyToOne
    private   ApplicationUser applicationUser ;
    @ManyToOne
    private   PostApplication postApplication ;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt")
    private Date createdAt;

    public CommentsApplication(){

    }

    public CommentsApplication(String body, ApplicationUser userDetails) {
        this.commentBody=body;
        this.applicationUser=userDetails;
    }
    public CommentsApplication(String body, ApplicationUser userDetails,PostApplication postApplication) {
        this.commentBody=body;
        this.applicationUser=userDetails;
        this.postApplication=postApplication;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
