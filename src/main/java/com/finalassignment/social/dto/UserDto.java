package com.finalassignment.social.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class UserDto {
    @Id
    private int userId;

    @NotBlank(message = "Username cannot be blank!")
    private String username;

    @NotBlank(message = "Password field cannot be blank!")
    private String password;
}
