package com.finalassignment.social.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.finalassignment.social.models.Post;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@ToString
public class UserProfileDto {
    @Id
    private int userId;

    @NotNull(message = "First Name cannot be left empty")
    private String firstName;

    @NotNull(message = "Last Name cannot be blank!")
    private String lastName;

    @NotNull(message = "Email is mandatory!")
    @Email(message = "Please enter a valid Email!")
    private String email;

    @JsonIgnore
    private Set<Post> associatedPosts;
}
