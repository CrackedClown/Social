package com.finalassignment.social.models;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tags")
@Getter
@Setter
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Tags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tagId;

    @Column(name = "tag_name", unique = true)
    @NotNull(message = "Tag name is must!")
    private String tagName;

    @ManyToMany(mappedBy = "associatedTags")
    @JsonBackReference
    private Set<Post> associatedPosts = new HashSet<>();
}