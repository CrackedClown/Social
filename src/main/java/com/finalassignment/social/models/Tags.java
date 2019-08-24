package com.finalassignment.social.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int tagId;

    @Column(name = "tag_name")
    private String tagName;

    @ManyToMany(mappedBy = "associatedTags")
    private Set<Post> associatedPosts = new HashSet<>();


}
