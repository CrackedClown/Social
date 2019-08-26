package com.finalassignment.social.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

   /* @OneToOne
    @MapsId
    private User user;*/

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Post> associatedPosts;
}
