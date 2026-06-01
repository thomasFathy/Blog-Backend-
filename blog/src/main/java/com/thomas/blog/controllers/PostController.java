package com.thomas.blog.controllers;


import com.thomas.blog.domain.dtos.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @GetMapping
    public ResponseEntity<List<PostDto>> getPosts(@RequestParam(required = false) UUID tagId,
                                                  @RequestParam(required = false) UUID categoryId){
                                                      return ResponseEntity.ok().build();


    }
}
