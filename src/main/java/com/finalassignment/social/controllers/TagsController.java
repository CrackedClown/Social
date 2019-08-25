package com.finalassignment.social.controllers;

import com.finalassignment.social.services.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagsController {
    TagsService tagsService;

    @Autowired
    TagsController(TagsService tagsService){
        this.tagsService = tagsService;
    }

    @DeleteMapping("/users/{id}/tags/{tag_id}")
    public void removeTagById(@PathVariable Integer tag_id){
        tagsService.removeTagById(tag_id);
    }
}
