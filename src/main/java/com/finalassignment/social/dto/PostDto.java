package com.finalassignment.social.dto;


import com.finalassignment.social.models.Tags;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@ToString
public class PostDto {

    @Id
    private int postId;

    @NotBlank(message = "Title is mandatory!")
    private String title;

    @NotBlank(message = "Content is mandatory!")
    private String content;

    @NotNull(message = "At least 1 tag is necessary!")
    private Set<Tags> associatedTags = new HashSet<>();
}
