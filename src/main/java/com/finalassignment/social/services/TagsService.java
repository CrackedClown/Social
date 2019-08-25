package com.finalassignment.social.services;

import com.finalassignment.social.models.Post;
import com.finalassignment.social.models.Tags;
import com.finalassignment.social.repositories.PostRepository;
import com.finalassignment.social.repositories.TagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Set;

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
        tagsRepository.deleteById(tag_id);
    }


}
