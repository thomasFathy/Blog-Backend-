package com.thomas.blog.services.impl;

import com.thomas.blog.domain.PostStatus;
import com.thomas.blog.domain.entities.Category;
import com.thomas.blog.domain.entities.Post;
import com.thomas.blog.domain.entities.Tag;
import com.thomas.blog.repositories.PostRepository;
import com.thomas.blog.services.CategoryService;
import com.thomas.blog.services.PostService;
import com.thomas.blog.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CategoryService categoryService;
    private final TagService tagService;


    @Override
    public List<Post> getPosts(UUID categoryId, UUID tagId) {
            if(categoryId != null && tagId!=null){
                Category category= categoryService.getCategoryById(categoryId);
                Tag tag = tagService.getTagById(tagId);

            return postRepository.findAllByStatusAndCategoryAndTagsContaining(PostStatus.PUBLISHED,category,tag);
            }

            if(categoryId !=null){
                Category category= categoryService.getCategoryById(categoryId);
                return postRepository.findAllByStatusAndCategory(PostStatus.PUBLISHED,category );
            }

            if(tagId!= null){
                Tag tags = tagService.getTagById(tagId);
                return postRepository.findAllByStatusAndTagsContaining(PostStatus.PUBLISHED, tags);
            }

        return postRepository.findAllByStatus(PostStatus.PUBLISHED);

    }



}

