package com.finalassignment.social.services;

import com.finalassignment.social.models.Post;
import com.finalassignment.social.models.Tags;
import com.finalassignment.social.models.UserProfile;
import com.finalassignment.social.repositories.PostRepository;
import com.finalassignment.social.repositories.UserProfileRepository;
import com.finalassignment.social.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Service
public class PostService {
    private PostRepository postRepository;
    private UserProfileRepository userProfileRepository;
    private EntityManager entityManager;

    @Autowired
    PostService(PostRepository postRepository, UserProfileRepository userProfileRepository, EntityManager entityManager) {
        this.postRepository = postRepository;
        this.userProfileRepository = userProfileRepository;
        this.entityManager = entityManager;
    }

    public Post createPostForId(Post post, Integer id) {
        post.setUserProfile(userProfileRepository.findById(id).orElse(null));
        return postRepository.save(post);
    }

    public Post getPostById(Integer post_id) {
        Post post = postRepository.findById(post_id).orElse(null);
        post.setViews(post.getViews() + 1);
        postRepository.save(post);
        return post;
    }

    public Post updatePostById(Integer post_id, Post post) {
        Post tempPost = postRepository.findById(post_id).orElse(null);
        tempPost.setTitle(post.getTitle());
        tempPost.setContent(post.getContent());
        return postRepository.save(tempPost);
    }

    public void removePostById(Integer post_id) {
        postRepository.deleteById(post_id);
    }

    public List<Post> getAllPosts() {
        postRepository.findAll().forEach(post -> post.setViews(post.getViews() + 1));
        postRepository.findAll().forEach(post -> postRepository.save(post));
        return postRepository.findAll();
    }

    public void likeAPostById(Integer post_id) {
        Post post = postRepository.findById(post_id).orElse(null);
        post.setLikes(post.getLikes() + 1);
        postRepository.save(post);
    }
}
