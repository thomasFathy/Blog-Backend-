package com.thomas.blog.repositories;

import com.thomas.blog.domain.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface TagRepository extends JpaRepository<Tag, UUID> {
@Query("SELECT t FROM Tag t LEFT JOIN Fetch t.posts")
    List<Tag> getAllTags();

    List<Tag> findByNameIn(Set<String> names);

}
