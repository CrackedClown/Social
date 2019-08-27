package com.finalassignment.social.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.finalassignment.social.models.Post;
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
