package com.finalassignment.social.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "post")
@Getter
@Setter
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Post {

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
    private Set<Tags> associatedTags = new HashSet<>();
}
