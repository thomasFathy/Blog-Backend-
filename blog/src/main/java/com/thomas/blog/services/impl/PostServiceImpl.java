package com.thomas.blog.services.impl;

import com.thomas.blog.domain.entities.Category;
import com.thomas.blog.domain.entities.Post;
import com.thomas.blog.repositories.PostRepository;
import com.thomas.blog.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;


    @Override
    public List<Post> getPosts(UUID categoryId, UUID tagId) {
//        List<Post> posts = postRepository.findAllById();
            if(categoryId != null && tagId!=null){

            }


            return null;
    }



}

