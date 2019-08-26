package com.finalassignment.social.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user_profile")
@Getter
@Setter
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @NotNull(message = "First Name cannot be left empty")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "Last Name cannot be blank!")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "Email is mandatory!")
    @Column(name = "email")
    @Email
    private String email;

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Post> associatedPosts;
}

/* @OneToOne
    @MapsId
    private User user;*/