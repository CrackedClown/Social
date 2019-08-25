package com.finalassignment.social.controllers;

import com.finalassignment.social.models.Post;
import com.finalassignment.social.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class PostController {
    private PostService postService;

    @Autowired
    PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping("/user/{id}/post")
    public Post createPost(@PathVariable Integer id, @RequestBody Post post){
        return postService.createPostForId(post, id);
    }

    @GetMapping("/user/{id}/post/{post_id}")
    public List<Post> getPosyById(@PathVariable Integer post_id){
        return postService.getPostsById(Collections.singleton(post_id));
    }

    @DeleteMapping("/user/{id}/post/{post_id}")
    public void removePostById(@PathVariable Integer post_id){
        postService.removePostById(post_id);
    }

}
