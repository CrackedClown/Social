package com.finalassignment.social.controllers;

import com.finalassignment.social.models.Post;
import com.finalassignment.social.models.Tags;
import com.finalassignment.social.services.TagsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Slf4j
@RestController
public class TagsController {
    TagsService tagsService;

    @Autowired
    TagsController(TagsService tagsService){
        this.tagsService = tagsService;
    }


    /**
     * To get all the tags
     * @return
     */
    @GetMapping("/users/{id}/tags")
    public ResponseEntity<List<Tags>> getAllTags() {
        log.debug("Getting All Tags, In TagsController");
        return ResponseEntity.status(HttpStatus.OK).body(tagsService.getAllTags());
    }

    /**
     * To get a post by TagName
     * @param tagName
     * @return
     */

    @GetMapping("/users/{id}/tags/{tagName}")
    public ResponseEntity<Set<Post>> getPostsByTag(@PathVariable String tagName) {
        log.debug("Getting Posts by Tag, In TagsController");
        return ResponseEntity.status(HttpStatus.OK).body(tagsService.getPostsByTag(tagName));
    }


    /**
     * To remove a tag by ID
     * @param tag_id
     * @return
     */
    @DeleteMapping("/users/{id}/tags/{tag_id}")
    public ResponseEntity<Void> removeTagById(@PathVariable Integer tag_id){
        log.debug("Removing Tag by ID, In TagsController");
        tagsService.removeTagById(tag_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
