package com.finalassignment.social.controllers;

import com.finalassignment.social.models.Post;
import com.finalassignment.social.services.PostService;
import com.finalassignment.social.services.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping("/users/{id}/posts")
    public Post createPost(@PathVariable Integer id, @Valid @RequestBody Post post) {
        return postService.createPostForId(post, id);
    }

    @GetMapping("/users/{id}/posts/{post_id}")
    public  Post getPostById(@PathVariable Integer post_id) {
        return postService.getPostById(post_id);
    }

    @DeleteMapping("/users/{id}/posts/{post_id}")
    public void removePostById(@PathVariable Integer post_id) {
        postService.removePostById(post_id);
    }

    @PutMapping("/users/{id}/posts/{post_id}")
    public Post updatePostById(@PathVariable(value = "post_id") Integer post_id, @Valid @RequestBody Post post){
        return postService.updatePostById(post_id, post);
    }
    @PutMapping("/users/{id}/posts/{post_id}/like")
    public void likeAPostById(@PathVariable("post_id") Integer post_id){
        postService.likeAPostById(post_id);
    }

}
