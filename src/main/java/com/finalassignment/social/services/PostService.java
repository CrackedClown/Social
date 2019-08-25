package com.finalassignment.social.services;

import com.finalassignment.social.models.Post;
import com.finalassignment.social.repositories.PostRepository;
import com.finalassignment.social.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private PostRepository postRepository;
    private UserRepository userRepository;

    @Autowired
    PostService(PostRepository postRepository, UserRepository userRepository){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Post createPostForId(Post post, Integer id){
        post.setUser(userRepository.findById(id).orElse(null));
        return postRepository.save(post);
    }

    public List<Post> getPostsById(Iterable<Integer> id){
        return postRepository.findAllById(id);
    }

    public void removePostById(Integer post_id){
        postRepository.deleteById(post_id);
    }
}
