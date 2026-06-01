package com.thomas.blog.mappers;

import com.thomas.blog.domain.PostStatus;
import com.thomas.blog.domain.dtos.TagResponse;
import com.thomas.blog.domain.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import com.thomas.blog.domain.entities.Tag;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {

    @Mapping(target = "postCount",source = "posts",qualifiedByName = "calculatePostCount")
    TagResponse toTagResponse(Tag tag);

    @Named("calculatePostCount")
    default Integer calculatePostCount(Set  <Post> posts){
    if(posts==null){
        return 0;
    }
    return (int)posts.stream().filter(post -> PostStatus.PUBLISHED.equals(post.getStatus())).count();



    }

}
