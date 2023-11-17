package com.blogapplication.payloads;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class PostDTO {

    private String postId;
    private String postTitle;
    private String content;

    private String imageName = "default.png";
    private Date postDate;

    private CategoryDTO category;

    private UserDTO  user;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<CommentDTO> comment=new HashSet<>();
}