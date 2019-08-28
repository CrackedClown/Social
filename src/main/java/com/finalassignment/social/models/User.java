package com.finalassignment.social.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
@Table(name = "user")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @NotBlank(message = "Username cannot be blank!")
    @Column(name = "username", unique = true, length = 25)
    private String username;

    @NotBlank(message = "Password field cannot be blank!")
    @Column(name = "password")
    private String password;

    @Column(name = "is_active")
    Boolean isActive = true;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private UserProfile userProfile;

    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;

}
