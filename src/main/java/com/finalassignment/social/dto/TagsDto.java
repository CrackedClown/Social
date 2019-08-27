package com.finalassignment.social.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
public class TagsDto {
    private int tagId;

    @NotNull(message = "Tag name is must!")
    private String tagName;

    private Set<Post> associatedPosts = new HashSet<>();
}
