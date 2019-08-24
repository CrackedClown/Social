package com.finalassignment.social.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "tags", cascade = CascadeType.ALL)
    private List<Post> associatedPosts;
}
