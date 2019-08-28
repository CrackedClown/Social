package com.finalassignment.social.controllers;

import com.finalassignment.social.exceptions.IllegalModificationException;
import com.finalassignment.social.exceptions.PostNotFoundException;
import com.finalassignment.social.exceptions.TagListEmptyException;
import com.finalassignment.social.exceptions.UserNotFoundException;
import com.finalassignment.social.models.Post;
import com.finalassignment.social.services.PostService;
import com.finalassignment.social.services.TagsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
public class PostController {
    private PostService postService;
    private TagsService tagsService;

    @Autowired
    PostController(PostService postService, TagsService tagsService) {
        this.postService = postService;
        this.tagsService = tagsService;
    }

    /**
     * To get all the posts
     * @return
     */

    @GetMapping("/users/{id}/posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        log.debug("Getting All Posts, In PostController");
        return ResponseEntity.status(HttpStatus.OK).body(postService.getAllPosts());
    }

    /**
     * To create a new Post
     * @param id
     * @param post
     * @return
     * @throws UserNotFoundException
     * @throws IllegalModificationException
     */
    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> createPost(@PathVariable Integer id, @Valid @RequestBody Post post) throws UserNotFoundException, IllegalModificationException, TagListEmptyException {
        log.debug("Creating Post, In PostController");
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPostForId(post, id));
    }


    /**
     * To search a post by Id
     * @param post_id
     * @return
     * @throws PostNotFoundException
     */
    @GetMapping("/users/{id}/posts/{post_id}")
    public ResponseEntity<Post> getPostById(@PathVariable Integer post_id) throws PostNotFoundException {
        log.debug("Getting a specific Post, In PostController");
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPostById(post_id));
    }

    /**
     * To remove a post by ID
     * @param post_id
     * @return
     */
    @DeleteMapping("/users/{id}/posts/{post_id}")
    public ResponseEntity<Void> removePostById(@PathVariable Integer post_id) {
        log.debug("Removing Post, In PostController");
        postService.removePostById(post_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * To update a post by ID
     * @param post_id
     * @param post
     * @return
     * @throws PostNotFoundException
     * @throws IllegalModificationException
     */
    @PutMapping("/users/{id}/posts/{post_id}")
    public ResponseEntity<Post> updatePostById(@PathVariable(value = "post_id") Integer post_id, @Valid @RequestBody Post post) throws PostNotFoundException, IllegalModificationException {
        log.debug("Updating Post, In PostController");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(postService.updatePostById(post_id, post));
    }

    /**
     * To like a post by ID
     * @param post_id
     * @param id
     * @return
     * @throws UserNotFoundException
     * @throws PostNotFoundException
     */
    @PutMapping("/users/{id}/posts/{post_id}/like")
    public ResponseEntity<Void> likeAPostById(@PathVariable("post_id") Integer post_id, @PathVariable("id") Integer id) throws UserNotFoundException, PostNotFoundException {
        log.debug("Liking Post, In PostController");
        postService.likeAPostById(post_id, id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
