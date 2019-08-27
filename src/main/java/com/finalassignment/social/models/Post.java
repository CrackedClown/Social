package com.finalassignment.social.models;


import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "post")
@Getter
@Setter
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Post {
    Post(){

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;

    @Column(name = "title")
    @NotBlank(message = "Title is mandatory!")
    private String title;

    @Column(name = "content")
    @NotBlank(message = "Content is mandatory!")
    private String content;

    @Column(name = "likes")
    private int likes;

    @Column(name = "views")
    private int views;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private UserProfile userProfile;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "posts_tags",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    @NotNull(message = "At least 1 tag is necessary!")
    private Set<Tags> associatedTags = new HashSet<>();
}
