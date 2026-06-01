package com.thomas.blog.services.impl;

import com.thomas.blog.domain.entities.Category;
import com.thomas.blog.repositories.CategoryRepository;
import com.thomas.blog.services.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAllWithPostCount();
    }


    @Override
    public Category createCategory(Category category) {
       if (categoryRepository.existsByNameIgnoreCase(category.getName())){
           throw new IllegalArgumentException("Category with this name already exists yabny");
       }
        return categoryRepository.save(category);
    }


    @Override
    public void deleteCategory(UUID id){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            if(!category.get().getPosts().isEmpty()){
                throw new IllegalStateException("This category has posts associated with");
            }
            categoryRepository.deleteById(id);
        }
    }

    @Override
    public Category getCategoryById(UUID id) {
        return categoryRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Category not found with id: "+id));
    }


}
