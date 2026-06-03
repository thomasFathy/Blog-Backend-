package com.thomas.blog.controllers;


import com.thomas.blog.domain.dtos.PostDto;
import com.thomas.blog.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
   private final PostService postService;

    @GetMapping
    public ResponseEntity<List<PostDto>> getPosts(@RequestParam(required = false) UUID tagId,
                                                  @RequestParam(required = false) UUID categoryId){



        postService.getPosts(tagId, categoryId);
        return ResponseEntity.ok().build();


    }
}
