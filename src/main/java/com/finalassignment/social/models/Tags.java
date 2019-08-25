package com.finalassignment.social.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tags")
@Getter
@Setter
@ToString

public class Tags{
    public Tags(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tagId;

    @Column(name = "tag_name")
    private String tagName;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "associatedTags")
    private Set<Post> associatedPosts = new HashSet<>();
}