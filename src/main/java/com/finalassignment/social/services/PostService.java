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
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Service
public class PostService {
    private PostRepository postRepository;
    private UserProfileRepository userProfileRepository;
    private EntityManager entityManager;

    @Autowired
    PostService(PostRepository postRepository, UserProfileRepository userProfileRepository, EntityManager entityManager){
        this.postRepository = postRepository;
        this.userProfileRepository = userProfileRepository;
        this.entityManager = entityManager;
    }

    public Post createPostForId(Post post, Integer id){
        post.setUserProfile(userProfileRepository.findById(id).orElse(null));
        return postRepository.save(post);
    }

    public List<Post> getPostsById(Iterable<Integer> id){
        return postRepository.findAllById(id);
    }

    public void removePostById(Integer post_id){
        postRepository.deleteById(post_id);
    }

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public List<Post> getPostsByTag(String tagName) {
        TypedQuery<Tags> query = entityManager.createQuery(
                "SELECT t FROM Tags t WHERE t.tagName = :name", Tags.class);
        query.setParameter("name", tagName);
        List<Tags> tagsList = query.getResultList();
        List<Post> postList = new ArrayList<>();
        for(Tags tag : tagsList){
            postList.addAll(tag.getAssociatedPosts());
        }
        return postList;
    }
}
