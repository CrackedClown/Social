package com.finalassignment.social.controllers;

import com.finalassignment.social.models.Post;
import com.finalassignment.social.models.Tags;
import com.finalassignment.social.services.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class TagsController {
    TagsService tagsService;

    @Autowired
    TagsController(TagsService tagsService){
        this.tagsService = tagsService;
    }

    @GetMapping("/users/{id}/tags")
    public List<Tags> getAllTags() {
        return tagsService.getAllTags();
    }

    @GetMapping("/users/{id}/tags/{tagName}")
    public Set<Post> getPostsByTag(@PathVariable String tagName) {
        return tagsService.getPostsByTag(tagName);
    }

    @DeleteMapping("/users/{id}/tags/{tag_id}")
    public void removeTagById(@PathVariable Integer tag_id){
        tagsService.removeTagById(tag_id);
    }
}
