package com.finalassignment.social.controllers;

import com.finalassignment.social.models.Post;
import com.finalassignment.social.services.PostService;
import com.finalassignment.social.services.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
public class PostController {
    private PostService postService;
    private TagsService tagsService;

    @Autowired
    PostController(PostService postService, TagsService tagsService){
        this.postService = postService;
        this.tagsService = tagsService;
    }

    @PostMapping("/user/{id}/post")
    public Post createPost(@PathVariable Integer id, @RequestBody Post post){
        return postService.createPostForId(post, id);
    }

    @GetMapping("/user/{id}/post/{post_id}")
    public List<Post> getPostById(@PathVariable Integer post_id){
        return postService.getPostsById(Collections.singleton(post_id));
    }

    @DeleteMapping("/user/{id}/post/{post_id}")
    public void removePostById(@PathVariable Integer post_id){
        postService.removePostById(post_id);
    }

    @GetMapping("/user/{id}/post/tags/{tagName}")
    public Set<Post> getPostsByTag(@PathVariable String tagName){
        return tagsService.getPostsByTag(tagName);
    }

}
