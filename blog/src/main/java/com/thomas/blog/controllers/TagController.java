package com.thomas.blog.controllers;


import com.thomas.blog.domain.dtos.CreateTagsRequest;
import com.thomas.blog.domain.dtos.TagResponse;
import com.thomas.blog.domain.entities.Tag;
import com.thomas.blog.mappers.TagMapper;
import com.thomas.blog.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tags")

public class TagController {
    private final TagService tagService;
    private final TagMapper tagMapper;


    @GetMapping
    public ResponseEntity<List<TagResponse>> getAllTags(){
       List<Tag> tags =tagService.getAllTags();
       List<TagResponse>tagResponses=tags.stream().map(tagMapper::toTagResponse).toList();
       return ResponseEntity.ok(tagResponses);

    }

    @PostMapping
    public ResponseEntity<List<TagResponse>> createTags(@RequestBody CreateTagsRequest tagsRequest){
      List<Tag> savedTags=tagService.createTags(tagsRequest.getNames());
      List<TagResponse> tagsResponses= savedTags.stream().map(tagMapper::toTagResponse).toList();
        return ResponseEntity.ok(tagsResponses);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable UUID id){
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }

}
