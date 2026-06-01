package com.thomas.blog.controllers;


import com.thomas.blog.domain.dtos.CategoryDto;
import com.thomas.blog.domain.dtos.CreateCategoryRequest;
import com.thomas.blog.domain.entities.Category;
import com.thomas.blog.mappers.CategoryMapper;
import com.thomas.blog.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> listCategories(){
// this is my way

        List<CategoryDto> categories= new ArrayList<>();
        categories=categoryService.listCategories().stream()
                .map(categoryMapper::toDto)
                .toList();

// it's the original way

//List<Category> categories= categoryService.listCategories();
        return ResponseEntity.ok(categories) ;

    }


    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody @Valid CreateCategoryRequest createCategoryRequest){

        Category createCategory = categoryMapper.toEntity(createCategoryRequest);
        Category createdCategory =categoryService.createCategory(createCategory);

        return new ResponseEntity<>(categoryMapper.toDto(createdCategory), HttpStatus.CREATED);

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id ){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }



}
