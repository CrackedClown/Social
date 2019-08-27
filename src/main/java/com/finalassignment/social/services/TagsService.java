package com.finalassignment.social.services;

import com.finalassignment.social.models.Post;
import com.finalassignment.social.models.Tags;
import com.finalassignment.social.repositories.PostRepository;
import com.finalassignment.social.repositories.TagsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class TagsService {
    private TagsRepository tagsRepository;
    private PostRepository postRepository;
    private EntityManager entityManager;

    @Autowired
    TagsService(TagsRepository tagsRepository, PostRepository postRepository, EntityManager entityManager) {
        this.tagsRepository = tagsRepository;
        this.postRepository = postRepository;
        this.entityManager = entityManager;
    }

    public void removeTagById(Integer tag_id){
        log.debug("Removing Tag by ID, In TagsService");
        tagsRepository.deleteById(tag_id);
    }

    public Set<Post> getPostsByTag(String tagName) {
        log.debug("Getting Posts by Tag, In TagsService");
        TypedQuery<Tags> query = entityManager.createQuery("SELECT t FROM Tags t WHERE t.tagName = :name", Tags.class);
        query.setParameter("name", tagName);
        List<Tags> tagsList = query.getResultList();
        Set<Post> postList = new HashSet<>();
        for(Tags tag : tagsList){
            postList.addAll(tag.getAssociatedPosts());
        }
        postList.forEach(post -> post.setViews(post.getViews() + 1));
        postList.forEach(post -> postRepository.save(post));
        return postList;
    }

    public List<Tags> getAllTags(){
        log.debug("Getting All Tags, In TagsService");
        return tagsRepository.findAll();
    }
}
