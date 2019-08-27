package com.finalassignment.social.controllers;

import com.finalassignment.social.exceptions.IllegalModificationException;
import com.finalassignment.social.exceptions.PostNotFoundException;
import com.finalassignment.social.exceptions.UserNotFoundException;
import com.finalassignment.social.models.Post;
import com.finalassignment.social.services.PostService;
import com.finalassignment.social.services.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
public class PostController {
    private PostService postService;
    private TagsService tagsService;

    @Autowired
    PostController(PostService postService, TagsService tagsService) {
        this.postService = postService;
        this.tagsService = tagsService;
    }

    @GetMapping("/users/{id}/posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getAllPosts());
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> createPost(@PathVariable Integer id, @Valid @RequestBody Post post) throws UserNotFoundException, IllegalModificationException {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPostForId(post, id));
    }

    @GetMapping("/users/{id}/posts/{post_id}")
    public ResponseEntity<Post> getPostById(@PathVariable Integer post_id) throws PostNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPostById(post_id));
    }

    @DeleteMapping("/users/{id}/posts/{post_id}")
    public ResponseEntity<Void> removePostById(@PathVariable Integer post_id) {
        postService.removePostById(post_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/users/{id}/posts/{post_id}")
    public ResponseEntity<Post> updatePostById(@PathVariable(value = "post_id") Integer post_id, @Valid @RequestBody Post post) throws PostNotFoundException, IllegalModificationException {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(postService.updatePostById(post_id, post));
    }
    @PutMapping("/users/{id}/posts/{post_id}/like")
    public ResponseEntity<Void> likeAPostById(@PathVariable("post_id") Integer post_id){
        postService.likeAPostById(post_id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
