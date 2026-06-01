package com.thomas.blog.domain.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTagsRequest {
    @NotEmpty(message = "At least one name is required")
    @Size(max = 10,message = "At maximum {max} tags are allowed")
    private Set< @Size(min = 2,max = 10, message = "At least 2 chars, at maximum 10 chars")
            @Pattern(regexp = "^[\\w\\s-]+$",message = "Tags' name must be chars, letters")
            String> names;

}



