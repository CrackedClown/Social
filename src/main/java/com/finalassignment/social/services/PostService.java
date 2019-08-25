package com.finalassignment.social.services;

import com.finalassignment.social.models.Post;
import com.finalassignment.social.models.Tags;
import com.finalassignment.social.repositories.PostRepository;
import com.finalassignment.social.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

@Service
public class PostService {
    private PostRepository postRepository;
    private UserRepository userRepository;
    private EntityManager entityManager;

    @Autowired
    PostService(PostRepository postRepository, UserRepository userRepository, EntityManager entityManager){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.entityManager = entityManager;
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

    public Set<Post> getPostsByTag(String tagName) {
        TypedQuery<Tags> query = entityManager.createQuery(
                "SELECT t FROM Tags t WHERE t.tagName = :name", Tags.class);
        query.setParameter("name", tagName);
        Tags tag = query.getSingleResult();
        return tag.getAssociatedPosts();
    }
}
