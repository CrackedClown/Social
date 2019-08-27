package com.finalassignment.social.services;

import com.finalassignment.social.exceptions.IllegalModificationException;
import com.finalassignment.social.exceptions.PostNotFoundException;
import com.finalassignment.social.exceptions.UserNotFoundException;
import com.finalassignment.social.models.Post;
import com.finalassignment.social.repositories.PostRepository;
import com.finalassignment.social.repositories.TagsRepository;
import com.finalassignment.social.repositories.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;


@Service
public class PostService {
    private PostRepository postRepository;
    private UserProfileRepository userProfileRepository;
    private EntityManager entityManager;
    private TagsRepository tagsRepository;

    @Autowired
    PostService(PostRepository postRepository, UserProfileRepository userProfileRepository, EntityManager entityManager, TagsRepository tagsRepository) {
        this.postRepository = postRepository;
        this.userProfileRepository = userProfileRepository;
        this.entityManager = entityManager;
        this.tagsRepository = tagsRepository;
    }

    public Post createPostForId(Post post, Integer id) throws UserNotFoundException, IllegalModificationException {
        if(post.getLikes() > 0 || post.getViews() > 0){
            throw new IllegalModificationException("You're not allowed to change Likes or View status");
        }
        post.setUserProfile(userProfileRepository.findById(id).orElseThrow(( ) -> new UserNotFoundException("UserNotFound")));
        return postRepository.save(post);
    }

    public Post getPostById(Integer post_id) throws PostNotFoundException {
        Post post = postRepository.findById(post_id).orElseThrow(( ) -> new PostNotFoundException("PostNotFound"));
        post.setViews(post.getViews() + 1);
        postRepository.save(post);
        return post;
    }

    public Post updatePostById(Integer post_id, Post post) throws PostNotFoundException, IllegalModificationException {
        Post tempPost = postRepository.findById(post_id).orElseThrow(( ) -> new PostNotFoundException("PostNotFound"));
        if(post.getLikes() != tempPost.getLikes() || post.getViews() != tempPost.getViews()){
            throw new IllegalModificationException("You're not allowed to change Likes or View status");
        }

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
