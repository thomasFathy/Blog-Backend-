package com.thomas.blog.services;
import com.thomas.blog.domain.entities.Post;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Post> getPosts(UUID categoryId,UUID tagId);
}
