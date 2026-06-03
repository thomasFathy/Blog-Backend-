package com.thomas.blog.services.impl;

import com.thomas.blog.domain.entities.Tag;
import com.thomas.blog.mappers.TagMapper;
import com.thomas.blog.repositories.TagRepository;
import com.thomas.blog.services.TagService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.getAllTags();
    }


    @Transactional
    @Override
    public List<Tag> createTags(Set<String> tagNames) {
        List<Tag> alreadyExistedTags= tagRepository.findByNameIn(tagNames);

        Set<String> existingTagNames = alreadyExistedTags.stream().map(Tag::getName).collect(Collectors.toSet());

        List<Tag> newTags= tagNames.stream()
                .filter(name->!existingTagNames.contains(name))
                .map(name->
                        Tag.builder()
                                .name(name)
                                .posts(new HashSet<>())
                                .build()).toList();


        List<Tag> savedTags= new ArrayList<>();
        if(!newTags.isEmpty()){

            savedTags=tagRepository.saveAll(newTags);

        }
        savedTags.addAll(alreadyExistedTags);

        return savedTags;
    }

    @Transactional
    @Override
    public void deleteTag(UUID id) {
//        Optional<Tag> tag=tagRepository.findById(id);
        tagRepository.findById(id).ifPresent(tag -> {
            if(!tag.getPosts().isEmpty()){
                throw new IllegalStateException("Cannot delete tag with posts");
            }
            tagRepository.deleteById(id);
            });

    }

    @Override
    public Tag getTagById(UUID tagId) {
        return tagRepository.findById(tagId).orElseThrow(()-> new RuntimeException("No tag found with this id"));
    }


}
